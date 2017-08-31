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
package org.apache.syncope.core.persistence.api.entity;

import java.util.List;
import java.util.Set;
import org.apache.syncope.common.lib.types.SAML2BindingType;

public interface SAML2IdP extends Entity {

    String getEntityID();

    void setEntityID(String entityID);

    String getName();

    void setName(String name);

    byte[] getMetadata();

    void setMetadata(byte[] metadata);

    boolean isCreateUnmatching();

    void setCreateUnmatching(boolean createUnmatching);

    boolean isUpdateMatching();

    void setUpdateMatching(boolean updateMatching);

    boolean isUseDeflateEncoding();

    void setUseDeflateEncoding(boolean useDeflateEncoding);

    boolean isSupportUnsolicited();

    void setSupportUnsolicited(boolean supportUnsolicited);

    SAML2BindingType getBindingType();

    void setBindingType(SAML2BindingType bindingType);

    SAML2UserTemplate getUserTemplate();

    void setUserTemplate(SAML2UserTemplate userTemplate);

    SAML2IdPItem getConnObjectKeyItem();

    void setConnObjectKeyItem(SAML2IdPItem item);

    boolean add(SAML2IdPItem item);

    List<? extends SAML2IdPItem> getItems();

    Set<String> getActionsClassNames();

}
