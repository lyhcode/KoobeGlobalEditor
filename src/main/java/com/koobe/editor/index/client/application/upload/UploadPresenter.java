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

package com.koobe.editor.index.client.application.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.typedarrays.shared.Int8Array;
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
import com.koobe.editor.index.shared.dispatch.SendFileToServerAction;
import com.koobe.editor.index.shared.dispatch.SendFileToServerResult;
import com.koobe.editor.index.client.application.ApplicationPresenter;
import com.koobe.editor.index.client.place.NameTokens;
import com.koobe.editor.login.client.LoginService;
import com.koobe.editor.login.client.LoginServiceAsync;

public class UploadPresenter extends Presenter<UploadPresenter.MyView, UploadPresenter.MyProxy>
        implements UploadUiHandlers {

    private final UploadServiceAsync uploadService = GWT.create(UploadService.class);

    private final DispatchAsync dispatcher;

    /**
     * {@link UploadPresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(NameTokens.uploadPage)
    public interface MyProxy extends ProxyPlace<UploadPresenter> {
    }

    /**
     * {@link UploadPresenter}'s view.
     */
    public interface MyView extends View, HasUiHandlers<UploadUiHandlers> {
        void updateSendTextResult(String s);
    }

    @Inject
    public UploadPresenter(EventBus eventBus,
                           MyView view,
                           MyProxy proxy,
                           DispatchAsync dispatcher) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;

        getView().setUiHandlers(this);
    }

    @Override
    public void sendFileChunk(String chunk) {

        uploadService.upload(chunk, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(String result) {
                getView().updateSendTextResult(result);
            }
        });

    }
}
