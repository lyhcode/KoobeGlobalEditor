package com.koobe.editor.common.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.koobe.editor.common.client.uploader.UploadChunk;
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

    public String uploadChunk(long index, String chunk, boolean base64Encoding) throws IllegalArgumentException {

        File file = new File("/tmp/test.pdf");
        try {
            FileOutputStream stream = new FileOutputStream(file, true);
            stream.write(base64Encoding?base64decoder.decodeBuffer(chunk):chunk.getBytes());
            stream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return "ok";
    }

    public String uploadChunk(long index, byte[] chunkData) throws IllegalArgumentException {

        File file = new File("/tmp/test.pdf");

        try {
            FileOutputStream stream = new FileOutputStream(file, true);
            stream.write(chunkData);
            stream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return "ok";
    }

    public String uploadChunk(long index, UploadChunk chunk) throws IllegalArgumentException {

        File file = new File("/tmp/test.pdf");

        try {
            FileOutputStream stream = new FileOutputStream(file, true);
            stream.write(chunk.getChunkData());
            stream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return "ok";
    }
}
