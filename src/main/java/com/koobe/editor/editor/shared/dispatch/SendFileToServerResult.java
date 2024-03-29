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

import com.gwtplatform.dispatch.shared.Result;

/**
 * The result of a {@link com.koobe.editor.editor.shared.dispatch.SendFileToServerAction} action.
 */
public class SendFileToServerResult implements Result {
    private String response;

    public SendFileToServerResult(final String response) {
        this.response = response;
    }

    /**
     * For serialization only.
     */
    @SuppressWarnings("unused")
    private SendFileToServerResult() {
    }

    public String getResponse() {
        return response;
    }
}
