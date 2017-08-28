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
var FLOWABLE = FLOWABLE || {};

FLOWABLE.URL = {

  getModel: function (modelId) {
    return window.location.toString().substr(0, window.location.toString().indexOf('/flowable-modeler'))
            + "/workflowDefGET?modelId=" + modelId;
  },

  getStencilSet: function () {
    return window.location.toString().substr(0, window.location.toString().indexOf('/flowable-modeler'))
            + "/flowable-modeler/stencilset.json";
  },

  putModel: function (modelId) {
    return window.location.toString().substr(0, window.location.toString().indexOf('/flowable-modeler'))
            + "/workflowDefPUT?modelId=" + modelId;
  },

  validateModel: function () {
    return FLOWABLE.CONFIG.contextRoot + '/app/rest/model/validate';
  }
};
