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
package org.apache.syncope.client.console.panels.search;

import java.util.List;
import org.apache.syncope.client.console.commons.Constants;
import org.apache.syncope.client.console.rest.GroupRestClient;
import org.apache.syncope.client.console.wizards.WizardMgtPanel;
import org.apache.syncope.client.console.wizards.any.AnyHandler;
import org.apache.syncope.common.lib.to.AnyTypeClassTO;
import org.apache.syncope.common.lib.to.GroupTO;
import org.apache.wicket.PageReference;

public final class GroupSelectionSearchResultPanel extends AnySelectionSearchResultPanel<GroupTO> {

    private static final long serialVersionUID = -1100228004207271271L;

    public static final String[] GROUP_DEFAULT_SELECTION = { "key", "name" };

    private GroupSelectionSearchResultPanel(final String id, final Builder builder) {
        super(id, builder);
    }

    @Override
    protected String paginatorRowsKey() {
        return Constants.PREF_GROUP_PAGINATOR_ROWS;
    }

    @Override
    protected String[] getDislayAttributes() {
        return GROUP_DEFAULT_SELECTION;
    }

    @Override
    public String getPrefDetailsView() {
        return Constants.PREF_GROUP_DETAILS_VIEW;
    }

    @Override
    public String getPrefAttributesView() {
        return Constants.PREF_GROUP_ATTRIBUTES_VIEW;
    }

    @Override
    public String getPrefDerivedAttributesView() {
        return Constants.PREF_GROUP_DERIVED_ATTRIBUTES_VIEW;
    }

    public static final class Builder extends AnySelectionSearchResultPanel.Builder<GroupTO> {

        private static final long serialVersionUID = 1L;

        public Builder(final List<AnyTypeClassTO> anyTypeClassTOs, final String type, final PageReference pageRef) {
            super(anyTypeClassTOs, new GroupRestClient(), type, pageRef);
            this.filtered = true;
            this.checkBoxEnabled = false;
        }

        @Override
        protected WizardMgtPanel<AnyHandler<GroupTO>> newInstance(final String id) {
            return new GroupSelectionSearchResultPanel(id, this);
        }
    }
}