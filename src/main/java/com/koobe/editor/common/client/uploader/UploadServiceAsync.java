package com.koobe.editor.common.client.uploader;

import com.google.gwt.typedarrays.shared.Int8Array;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.koobe.editor.login.shared.LoginResult;

public interface UploadServiceAsync {
    void uploadChunk(long index, String chunk, boolean base64Encoding, AsyncCallback<String> callback)
            throws IllegalArgumentException;
}
