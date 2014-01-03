package com.koobe.editor.index.client.application.upload;

import com.google.gwt.typedarrays.shared.Int8Array;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.koobe.editor.login.shared.LoginResult;

public interface UploadServiceAsync {
    void upload(String file, AsyncCallback<String> callback)
            throws IllegalArgumentException;
}
