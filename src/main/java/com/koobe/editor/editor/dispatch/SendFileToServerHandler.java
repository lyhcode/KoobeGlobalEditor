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

package com.koobe.editor.editor.dispatch;

import com.google.gwt.typedarrays.shared.Int8Array;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import com.koobe.editor.editor.shared.dispatch.SendFileToServerAction;
import com.koobe.editor.editor.shared.dispatch.SendFileToServerResult;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class SendFileToServerHandler implements ActionHandler<SendFileToServerAction, SendFileToServerResult> {
    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;

    @Inject
    SendFileToServerHandler(ServletContext servletContext,
                            Provider<HttpServletRequest> requestProvider) {
        this.servletContext = servletContext;
        this.requestProvider = requestProvider;
    }

    @Override
    public SendFileToServerResult execute(SendFileToServerAction action, ExecutionContext context)
            throws ActionException {
        Int8Array input = action.getTextToServer();


        String response = String.format("length: " + input.length());

        return new SendFileToServerResult(response);
    }

    @Override
    public Class<SendFileToServerAction> getActionType() {
        return SendFileToServerAction.class;
    }

    @Override
    public void undo(SendFileToServerAction action, SendFileToServerResult result, ExecutionContext context)
            throws ActionException {
        // Not undoable
    }
}
