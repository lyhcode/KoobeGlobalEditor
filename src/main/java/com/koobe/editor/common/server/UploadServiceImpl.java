package com.koobe.editor.common.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.koobe.editor.common.client.uploader.UploadService;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UploadServiceImpl extends RemoteServiceServlet implements
        UploadService {

    final static BASE64Decoder base64decoder = new BASE64Decoder();

    public String uploadChunk(long index, String chunk) throws IllegalArgumentException {

        File file = new File("/tmp/test.jpg");
        try {
            FileOutputStream stream = new FileOutputStream(file, true);
            //stream.write(base64decoder.decodeBuffer(chunk));
            stream.write(chunk.getBytes());
            stream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return "ok";
    }
}
