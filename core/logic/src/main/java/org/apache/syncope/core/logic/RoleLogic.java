/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.core.logic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.syncope.common.lib.to.RoleTO;
import org.apache.syncope.core.persistence.api.dao.NotFoundException;
import org.apache.syncope.core.persistence.api.dao.RoleDAO;
import org.apache.syncope.core.persistence.api.entity.Role;
import org.apache.syncope.core.provisioning.api.data.RoleDataBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class RoleLogic extends AbstractTransactionalLogic<RoleTO> {

    @Autowired
    private RoleDataBinder binder;

    @Autowired
    private RoleDAO roleDAO;

    @PreAuthorize("hasRole('ROLE_READ')")
    public RoleTO read(final Long roleKey) {
        Role role = roleDAO.find(roleKey);
        if (role == null) {
            LOG.error("Could not find role '" + roleKey + "'");

            throw new NotFoundException(String.valueOf(roleKey));
        }

        return binder.getRoleTO(role);
    }

    @PreAuthorize("hasRole('ROLE_LIST')")
    public List<RoleTO> list() {
        return CollectionUtils.collect(roleDAO.findAll(), new Transformer<Role, RoleTO>() {

            @Override
            public RoleTO transform(final Role input) {
                return binder.getRoleTO(input);
            }
        }, new ArrayList<RoleTO>());
    }

    @PreAuthorize("hasRole('ROLE_CREATE')")
    public RoleTO create(final RoleTO roleTO) {
        return binder.getRoleTO(roleDAO.save(binder.create(roleTO)));
    }

    @PreAuthorize("hasRole('ROLE_UPDATE')")
    public RoleTO update(final RoleTO roleTO) {
        Role role = roleDAO.find(roleTO.getKey());
        if (role == null) {
            LOG.error("Could not find role '" + roleTO.getKey() + "'");
            throw new NotFoundException(String.valueOf(roleTO.getKey()));
        }

        binder.update(role, roleTO);
        role = roleDAO.save(role);

        return binder.getRoleTO(role);
    }

    @PreAuthorize("hasRole('ROLE_DELETE')")
    public RoleTO delete(final Long roleKey) {
        Role role = roleDAO.find(roleKey);
        if (role == null) {
            LOG.error("Could not find role '" + roleKey + "'");

            throw new NotFoundException(String.valueOf(roleKey));
        }

        RoleTO deleted = binder.getRoleTO(role);
        roleDAO.delete(roleKey);
        return deleted;
    }

    @Override
    protected RoleTO resolveReference(final Method method, final Object... args)
            throws UnresolvedReferenceException {

        Long key = null;

        if (ArrayUtils.isNotEmpty(args)) {
            for (int i = 0; key == null && i < args.length; i++) {
                if (args[i] instanceof Long) {
                    key = (Long) args[i];
                } else if (args[i] instanceof RoleTO) {
                    key = ((RoleTO) args[i]).getKey();
                }
            }
        }

        if ((key != null) && !key.equals(0L)) {
            try {
                return binder.getRoleTO(roleDAO.find(key));
            } catch (Throwable ignore) {
                LOG.debug("Unresolved reference", ignore);
                throw new UnresolvedReferenceException(ignore);
            }
        }

        throw new UnresolvedReferenceException();
    }

}