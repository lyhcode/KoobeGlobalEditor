package com.koobe.editor.common.server.uploader;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.koobe.common.core.KoobeApplication;
import com.koobe.common.data.KoobeDataService;
import com.koobe.common.storage.KoobeStorageService;
import com.koobe.common.storage.impl.KoobeStorage;
import com.koobe.editor.common.client.uploader.UploadChunk;
import com.koobe.editor.common.client.uploader.UploadService;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class UploadServiceImpl extends RemoteServiceServlet implements
        UploadService {

    private final static BASE64Decoder base64decoder = new BASE64Decoder();

    private final static String DEFAULT_BUCKET_NAME = "koobe-tmp";

    private final static KoobeApplication application;
    private final static KoobeStorageService storageService;
    private final static KoobeStorage storage;

    private static File tempDirectory;

    static {
        application = KoobeApplication.getInstance();
        storageService = (KoobeStorageService)application.getService(KoobeStorageService.class);
        storage = storageService.getS3Storage(DEFAULT_BUCKET_NAME);

        try {
            tempDirectory = File.createTempFile(UploadServiceImpl.class.getName(), String.valueOf(new Date().getTime()));
            tempDirectory.delete();
            tempDirectory.mkdir();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String transferBegin() throws IllegalArgumentException {
        return getTransferId();
    }

    @Override
    public void transferEnd(String transferId, String fileName, String contentType) throws IllegalArgumentException, IOException {
        File file = getTransferFile(transferId);

        if (file.exists() && file.isFile()) {
            storage.putFile(fileName, file);
        }
    }

    private File getTransferFile(String transferId) throws IOException {
        return new File(tempDirectory, transferId);
    }

    private String getTransferId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void uploadChunk(String transferId, long index, String chunk, boolean base64Encoding)
            throws IllegalArgumentException, IOException {

        File file = getTransferFile(transferId);

        try {
            FileOutputStream stream = new FileOutputStream(file, true);
            stream.write(base64Encoding?base64decoder.decodeBuffer(chunk):chunk.getBytes());
            stream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void uploadChunk(String transferId, long index, byte[] chunkData)
            throws IllegalArgumentException, IOException {

        File file = getTransferFile(transferId);

        try {
            FileOutputStream stream = new FileOutputStream(file, true);
            stream.write(chunkData);
            stream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void uploadChunk(String transferId, long index, UploadChunk chunk)
            throws IllegalArgumentException, IOException {

        File file = getTransferFile(transferId);

        try {
            FileOutputStream stream = new FileOutputStream(file, true);
            stream.write(chunk.getChunkData());
            stream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
