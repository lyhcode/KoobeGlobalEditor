package com.koobe.editor.common.client.uploader;

import com.google.gwt.typedarrays.shared.Int8Array;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.koobe.editor.login.shared.LoginResult;

import java.io.IOException;

public interface UploadServiceAsync {


    /**
     * Start a transfer job.
     *
     * @return transferId
     * @throws IllegalArgumentException
     */
    void transferBegin(AsyncCallback<String> callback) throws  IllegalArgumentException;

    /**
     * Save transfer.
     *
     * @param transferId
     * @param fileName
     * @param contentType
     * @throws IllegalArgumentException
     */
    void transferEnd(String transferId, String fileName, String contentType, AsyncCallback<Void> callback)
            throws IllegalArgumentException, IOException;

    void uploadChunk(String transferId, long index, byte[] chunkData, AsyncCallback<Void> callback)
            throws IllegalArgumentException, IOException;

    void uploadChunk(String transferId, long index, UploadChunk chunk, AsyncCallback<Void> callback)
            throws IllegalArgumentException, IOException;

    void uploadChunk(String transferId, long index, String chunk, boolean base64Encoding, AsyncCallback<Void> callback)
            throws IllegalArgumentException, IOException;
}
