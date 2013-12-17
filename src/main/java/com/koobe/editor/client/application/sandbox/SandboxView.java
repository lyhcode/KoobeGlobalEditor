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

package com.koobe.editor.client.application.sandbox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.svenjacobs.gwtbootstrap3.bootbox.client.Bootbox;
import com.svenjacobs.gwtbootstrap3.bootbox.client.callback.AlertCallback;
import com.svenjacobs.gwtbootstrap3.bootbox.client.callback.ConfirmCallback;
import com.svenjacobs.gwtbootstrap3.bootbox.client.callback.PromptCallback;
import com.svenjacobs.gwtbootstrap3.client.ui.Button;
import com.svenjacobs.gwtbootstrap3.client.ui.TextBox;

public class SandboxView extends ViewWithUiHandlers<SandboxUiHandlers> implements SandboxPresenter.MyView {

    interface Binder extends UiBinder<Widget, SandboxView> {
    }

    @Inject
    public SandboxView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @UiField
    HTML text1;

    @Override
    public void updateText1(String html) {
        text1.setHTML(html);
    }

    @UiField
    HTML sendTextResultHTML;
    @UiField
    TextBox sendNameTextBox;
    @UiField
    Button sendTextButton;

    @UiHandler("sendTextButton")
    void onSendTextButtonClick(ClickEvent event) {
        getUiHandlers().sendName(sendNameTextBox.getText());
    }

    @Override
    public void updateSendTextResult(String html) {
        sendTextResultHTML.setHTML(html);
    }

    @UiField
    HTML dialogResult;
    @UiField
    Button alertButton;
    @UiField
    Button confirmButton;
    @UiField
    Button promptButton;

    @UiHandler("alertButton")
    void onAlertButtonClick(ClickEvent event) {
        Bootbox.alert("Hello", new AlertCallback() {
            @Override
            public void callback() {
                dialogResult.setHTML("alert dialog closed");
            }
        });
    }

    @UiHandler("confirmButton")
    void onConfirmButtonClick(ClickEvent event) {
        Bootbox.confirm("How are you?", new ConfirmCallback() {
            @Override
            public void callback(boolean result) {
                dialogResult.setHTML("return: <span class=\"label label-default\">" + result + "</span>");
            }
        });
    }

    @UiHandler("promptButton")
    void onPromptButtonClick(ClickEvent event) {
        Bootbox.prompt("Hello", new PromptCallback() {
            @Override
            public void callback(String result) {
                dialogResult.setHTML("return: " + result);
            }
        });
    }


}
