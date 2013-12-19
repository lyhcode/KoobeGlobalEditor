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

package com.koobe.editor.admin.client.application.sandbox;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
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

    @UiField
    HTMLPanel draftPanel;
    @UiField
    Button draftAddButton;
    @UiField
    Button draftAddContentButton;
    @UiField
    Button draftFetchButton;

    @UiHandler("draftAddButton")
    void onDraftAddButtonClick(ClickEvent event) {
        final HTML widget = new HTML("<h1>Chapter Title</h1>");
        final FocusPanel wrapper = new FocusPanel();
        wrapper.add(widget);
        wrapper.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent mouseOverEvent) {
                widget.setStyleName("draft-with-border");
            }
        });
        wrapper.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent mouseOutEvent) {
                widget.removeStyleName("draft-with-border");
            }
        });
        wrapper.addDoubleClickHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent doubleClickEvent) {
                Bootbox.prompt("Write:", new PromptCallback() {
                    @Override
                    public void callback(String result) {
                        widget.setHTML("<h1>" + result + "</h1>");
                    }
                });
            }
        });
        draftPanel.add(wrapper);
    }

    @UiHandler("draftAddContentButton")
    void onDraftAddContentButtonClick(ClickEvent event) {
        draftPanel.add(new FocusPanel(new HTML("<p>生於何處？完全不知。只記得在微黑濕濡的地方「咪咪」地哭泣著。在這裡，我第一次看見所謂人類。事後聽說，那是人類當中最壞的種族──書生返回。據說，這書生常常捕捉我們，煮來吃。可是，當時，我還沒有什麼思想，並不覺得特別害怕。他把我放在手掌上，忽地舉了起來，有點輕飃飃的感覺。在手掌上稍微鎮靜之後，才注視書生的臉，這是我第一次看到所謂人類。當時覺得很奇妙，這種奇妙感一直保留到現在。應該用毛做裝飾的臉，光滑發亮，有如水壺。後來，我也碰到許多貓，卻不曾遇見這樣殘缺的動物。臉蛋中央突起得太高，從那洞裡還不時噴出煙來，我被嗆得受不住。最近才知道那是人類所吸的香菸。</p>")));
    }

    @UiHandler("draftFetchButton")
    void onDraftFetchButtonClick(ClickEvent event) {
        Window.alert("HTML Count = " + draftPanel.getWidgetCount());

        StringBuffer all_html = new StringBuffer();

        for (int i = 0; i < draftPanel.getWidgetCount(); i++) {
            FocusPanel widget = (FocusPanel)draftPanel.getWidget(i);
            all_html.append(((HTML)widget.getWidget()).getHTML());
        }

        Bootbox.alert(all_html.toString());
    }

}
