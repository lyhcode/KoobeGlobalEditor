package com.koobe.editor.index.server;

import com.google.gwt.typedarrays.shared.Int8Array;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.koobe.common.core.KoobeApplication;
import com.koobe.common.data.KoobeDataService;
import com.koobe.common.data.domain.User;
import com.koobe.common.data.repository.UserRepository;
import com.koobe.editor.index.client.application.upload.UploadService;
import com.koobe.editor.login.shared.LoginResult;
import org.apache.commons.codec.binary.Base64InputStream;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UploadServiceImpl extends RemoteServiceServlet implements
        UploadService {

    public String upload(String chunk) throws IllegalArgumentException {

        File file = new File("/tmp/test.jpg");
        try {
            BASE64Decoder dec = new BASE64Decoder();

            FileOutputStream stream = new FileOutputStream(file, true);
            stream.write(dec.decodeBuffer(chunk));
            stream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return chunk;


    }
}
