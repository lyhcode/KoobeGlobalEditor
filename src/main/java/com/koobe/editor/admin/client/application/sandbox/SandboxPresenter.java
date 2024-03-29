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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.koobe.editor.admin.client.application.ApplicationPresenter;
import com.koobe.editor.admin.client.place.NameTokens;
import com.koobe.editor.admin.shared.dispatch.SendTextToServerAction;
import com.koobe.editor.admin.shared.dispatch.SendTextToServerResult;

public class SandboxPresenter extends Presenter<SandboxPresenter.MyView, SandboxPresenter.MyProxy>
        implements SandboxUiHandlers {

    /**
     * {@link SandboxPresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(NameTokens.sandboxPage)
    public interface MyProxy extends ProxyPlace<SandboxPresenter> {
    }

    /**
     * {@link SandboxPresenter}'s view.
     */
    public interface MyView extends View, HasUiHandlers<SandboxUiHandlers> {
        void updateText1(String html);

        void updateSendTextResult(String html);
    }

    private final DispatchAsync dispatcher;

    @Inject
    public SandboxPresenter(EventBus eventBus,
                            MyView view,
                            MyProxy proxy,
                            DispatchAsync dispatcher) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onReveal() {
        getView().updateText1("Update by " + getClass().getName());
    }

    @Override
    public void sendName(String name) {
        dispatcher.execute(new SendTextToServerAction(name), new AsyncCallback<SendTextToServerResult>() {
            @Override
            public void onFailure(Throwable caught) {
                getView().updateSendTextResult("An error occured: " + caught.getMessage());
            }

            @Override
            public void onSuccess(SendTextToServerResult result) {
                getView().updateSendTextResult(result.getResponse());
            }
        });
    }

}
