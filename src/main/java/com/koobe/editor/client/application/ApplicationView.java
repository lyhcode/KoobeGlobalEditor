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

package com.koobe.editor.client.application;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.svenjacobs.gwtbootstrap3.bootbox.client.Bootbox;
import com.svenjacobs.gwtbootstrap3.bootbox.client.callback.ConfirmCallback;
import com.svenjacobs.gwtbootstrap3.client.ui.ListItem;
import com.svenjacobs.gwtbootstrap3.client.ui.NavbarBrand;
import com.svenjacobs.gwtbootstrap3.client.ui.Row;

/**
 * This is the top-level view of the application. Every time another presenter wants to reveal itself,
 * {@link ApplicationView} will add its content of the target inside the {@code mainContantPanel}.
 */
public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    Row container;
    @UiField
    NavbarBrand brand;
    @UiField
    ListItem importLink;
    @UiField
    ListItem logoutLink;

    @Inject
    ApplicationView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == ApplicationPresenter.TYPE_SetMainContent) {
            container.clear();
            container.add(content);
        } else {
            super.setInSlot(slot, content);
        }
    }

    @UiHandler("logoutLink")
    void handleLogoutLinkClick(ClickEvent e) {
        Bootbox.confirm("Are you sure???", new ConfirmCallback() {
            @Override
            public void callback(boolean result) {
                if (result) {
                    String queryString = Window.Location.getQueryString();
                    Window.Location.assign("/login.html" + queryString);
                }
            }
        });

        e.preventDefault();
    }

    @Override
    public void showLoading(boolean visibile) {
        //...

    }
}
