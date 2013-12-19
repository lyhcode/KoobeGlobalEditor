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

<<<<<<< HEAD
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
=======
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
>>>>>>> add sandbox
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.koobe.editor.client.application.ApplicationPresenter;
import com.koobe.editor.client.place.NameTokens;
<<<<<<< HEAD
import com.koobe.editor.shared.dispatch.SendTextToServerAction;
import com.koobe.editor.shared.dispatch.SendTextToServerResult;

public class SandboxPresenter extends Presenter<SandboxPresenter.MyView, SandboxPresenter.MyProxy>
        implements SandboxUiHandlers {

=======

public class SandboxPresenter extends Presenter<SandboxPresenter.MyView, SandboxPresenter.MyProxy> {
>>>>>>> add sandbox
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
<<<<<<< HEAD
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
=======
    public interface MyView extends View {
        void updateText1(String html);
    }

    @Inject
    public SandboxPresenter(EventBus eventBus,
                            MyView view,
                            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
>>>>>>> add sandbox
    }

    @Override
    protected void onReveal() {
        getView().updateText1("Update by " + getClass().getName());
    }
<<<<<<< HEAD

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

=======
>>>>>>> add sandbox
}
