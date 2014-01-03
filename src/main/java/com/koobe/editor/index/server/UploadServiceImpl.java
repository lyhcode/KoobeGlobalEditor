package com.koobe.editor.index.server;

import com.google.gwt.typedarrays.shared.Int8Array;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.koobe.common.core.KoobeApplication;
import com.koobe.common.data.KoobeDataService;
import com.koobe.common.data.domain.User;
import com.koobe.common.data.repository.UserRepository;
import com.koobe.editor.index.client.application.upload.UploadService;
import com.koobe.editor.login.shared.LoginResult;

import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UploadServiceImpl extends RemoteServiceServlet implements
        UploadService {

    public String upload(String file) throws IllegalArgumentException {
        return file;
    }
}
