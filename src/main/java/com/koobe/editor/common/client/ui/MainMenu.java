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

package com.koobe.editor.common.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.svenjacobs.gwtbootstrap3.bootbox.client.Bootbox;
import com.svenjacobs.gwtbootstrap3.bootbox.client.callback.ConfirmCallback;
import com.svenjacobs.gwtbootstrap3.client.ui.ListItem;
import com.svenjacobs.gwtbootstrap3.client.ui.NavbarBrand;

/**
 * A simple menu that can be reused.
 */
public class MainMenu extends Composite {
    interface MainMenuUiBinder extends UiBinder<Widget, MainMenu> {
    }

    private static MainMenuUiBinder binder = GWT.create(MainMenuUiBinder.class);

    public MainMenu() {
        initWidget(binder.createAndBindUi(this));
    }

    @UiField
    NavbarBrand brand;
    @UiField
    ListItem logoutLink;

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
}
