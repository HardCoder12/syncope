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
package org.apache.syncope.ext.scimv2.cxf.service;

import java.util.List;
import java.util.UUID;
import javax.ws.rs.core.Response;
import org.apache.syncope.ext.scimv2.api.data.ListResponse;
import org.apache.syncope.ext.scimv2.api.data.SCIMGroup;
import org.apache.syncope.ext.scimv2.api.data.SCIMSearchRequest;
import org.apache.syncope.ext.scimv2.api.service.GroupService;
import org.apache.syncope.ext.scimv2.api.type.Resource;
import org.apache.syncope.ext.scimv2.api.type.SortOrder;

public class GroupServiceImpl extends AbstractService<SCIMGroup> implements GroupService {

    @Override
    public Response create() {
        return Response.
                created(uriInfo.getAbsolutePathBuilder().path(UUID.randomUUID().toString()).build()).
                build();
    }

    @Override
    public SCIMGroup read(final String id) {
        return binder().toSCIMGroup(groupLogic().read(id), uriInfo.getAbsolutePathBuilder().build().toASCIIString());
    }

    @Override
    public Response replace(final String id) {
        return Response.ok().build();
    }

    @Override
    public Response delete(final String id) {
        return Response.noContent().build();
    }

    @Override
    public Response update(final String id) {
        return Response.ok().build();
    }

    @Override
    public ListResponse<SCIMGroup> search(
            final List<String> attributes,
            final List<String> excludedAttributes,
            final String filter,
            final String sortBy,
            final SortOrder sortOrder,
            final Integer startIndex,
            final Integer count) {

        SCIMSearchRequest request = new SCIMSearchRequest(filter, sortBy, sortOrder, startIndex, count);
        if (attributes != null) {
            request.getAttributes().addAll(attributes);
        }
        if (excludedAttributes != null) {
            request.getExcludedAttributes().addAll(excludedAttributes);
        }

        return doSearch(Resource.Group, request);
    }

    @Override
    public ListResponse<SCIMGroup> search(final SCIMSearchRequest request) {
        return doSearch(Resource.Group, request);
    }
}
