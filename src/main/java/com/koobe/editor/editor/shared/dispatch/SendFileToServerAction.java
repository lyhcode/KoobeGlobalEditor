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

package com.koobe.editor.editor.shared.dispatch;

import com.google.gwt.typedarrays.shared.Int8Array;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * An action that can be sent using an (client-side) corresponding to a
 * {@link com.gwtplatform.dispatch.server.Dispatch} (server-side).
 */
public class SendFileToServerAction extends UnsecuredActionImpl<SendFileToServerResult> {
    private Int8Array textToServer;

    public SendFileToServerAction(Int8Array textToServer) {
        this.textToServer = textToServer;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private SendFileToServerAction() {
    }

    public Int8Array getTextToServer() {
        return textToServer;
    }
}
