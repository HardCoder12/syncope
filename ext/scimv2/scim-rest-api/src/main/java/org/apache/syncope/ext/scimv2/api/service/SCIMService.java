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
package org.apache.syncope.ext.scimv2.api.service;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.apache.syncope.ext.scimv2.api.SCIMConstants;
import org.apache.syncope.ext.scimv2.api.data.ResourceType;
import org.apache.syncope.ext.scimv2.api.data.SCIMResource;
import org.apache.syncope.ext.scimv2.api.data.ServiceProviderConfig;

@Path("v2")
public interface SCIMService extends SearchService<SCIMResource> {

    @GET
    @Path("ServiceProviderConfig")
    @Produces({ SCIMConstants.APPLICATION_SCIM_JSON })
    ServiceProviderConfig serviceProviderConfig();

    @GET
    @Path("ResourceTypes")
    @Produces({ SCIMConstants.APPLICATION_SCIM_JSON })
    List<ResourceType> resourceTypes();

    @GET
    @Path("ResourceTypes/{type}")
    @Produces({ SCIMConstants.APPLICATION_SCIM_JSON })
    ResourceType resourceType(@PathParam("type") String type);

    @GET
    @Path("Schemas")
    @Produces({ SCIMConstants.APPLICATION_SCIM_JSON })
    Response schemas();

    @GET
    @Path("Schemas/{schema}")
    @Produces({ SCIMConstants.APPLICATION_SCIM_JSON })
    Response schema(@PathParam("schema") String schema);
}
