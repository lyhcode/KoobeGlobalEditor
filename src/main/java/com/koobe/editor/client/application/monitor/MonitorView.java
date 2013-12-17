/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.koobe.editor.client.application.monitor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.svenjacobs.gwtbootstrap3.client.ui.Button;

import static com.google.gwt.query.client.GQuery.$;

public class MonitorView extends ViewImpl implements MonitorPresenter.MyView {
    interface Binder extends UiBinder<Widget, MonitorView> {
    }

    @Inject
    public MonitorView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @UiField
    Button refreshButton;

    @UiHandler("refreshButton")
    void onRefreshButtonClick(ClickEvent event) {
        //Window.alert("rest");

        $("#monitor-table-body").append("<tr><td>123</td></tr>");
    }
}
