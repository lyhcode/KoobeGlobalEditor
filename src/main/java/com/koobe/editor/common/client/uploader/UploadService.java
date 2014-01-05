package com.koobe.editor.common.client.uploader;

import com.google.gwt.typedarrays.shared.Int8Array;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.koobe.editor.login.shared.LoginResult;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("upload")
public interface UploadService extends RemoteService {
    String uploadChunk(long index, String chunk, boolean base64Encoding) throws IllegalArgumentException;
}
