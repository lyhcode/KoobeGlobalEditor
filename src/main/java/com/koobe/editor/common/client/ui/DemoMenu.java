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
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.bootbox.client.callback.ConfirmCallback;

/**
 * A simple menu that can be reused.
 */
public class DemoMenu extends Composite {
    interface Binder extends UiBinder<Widget, DemoMenu> {
    }

    private static Binder binder = GWT.create(Binder.class);

    public DemoMenu() {
        initWidget(binder.createAndBindUi(this));
    }

    @UiField
    InlineHyperlink logoutLink;

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
