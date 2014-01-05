package com.koobe.editor.common.client.uploader;

import com.google.gwt.typedarrays.shared.Int8Array;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.koobe.editor.login.shared.LoginResult;

import java.io.IOException;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("upload")
public interface UploadService extends RemoteService {

    /**
     * Start a transfer job.
     *
     * @return transferId
     * @throws IllegalArgumentException
     */
    String transferBegin() throws  IllegalArgumentException;

    /**
     * Save transfer.
     *
     * @param transferId
     * @param fileName
     * @param contentType
     * @throws IllegalArgumentException
     */
    void transferEnd(String transferId, String fileName, String contentType)
            throws IllegalArgumentException, IOException;

    void uploadChunk(String transferId, long index, byte[] chunkData)
            throws IllegalArgumentException, IOException;

    void uploadChunk(String transferId, long index, UploadChunk chunk)
            throws IllegalArgumentException, IOException;

    void uploadChunk(String transferId, long index, String chunk, boolean base64Encoding)
            throws IllegalArgumentException, IOException;
}
