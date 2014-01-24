package com.koobe.editor.common.server.uploader;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.koobe.common.converter.KoobeConverter;
import com.koobe.common.converter.KoobeConverterService;
import com.koobe.common.converter.KoobeConverterType;
import com.koobe.common.core.KoobeApplication;
import com.koobe.common.storage.KoobeStorageService;
import com.koobe.common.storage.impl.KoobeStorage;
import com.koobe.editor.common.client.uploader.UploadChunk;
import com.koobe.editor.common.client.uploader.UploadService;
import org.apache.commons.codec.binary.Base64;

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

    private final static Base64 base64decoder = new Base64();

    private final static String DEFAULT_BUCKET_NAME = "koobe-tmp";

    private final static KoobeApplication application;
    private final static KoobeStorageService storageService;
    private final static KoobeStorage storage;
    private final static KoobeConverterService converterService;


    private static File tempDirectory;

    static {
        application = KoobeApplication.getInstance();
        storageService = (KoobeStorageService)application.getService(KoobeStorageService.class);
        storage = storageService.getS3Storage(DEFAULT_BUCKET_NAME);
        converterService = (KoobeConverterService)application.getService(KoobeConverterService.class);

        try {
            String timestamp = String.valueOf(new Date().getTime());
            String className = UploadServiceImpl.class.getSimpleName();

            tempDirectory = File.createTempFile(className, timestamp);
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

        UploadContextListener.executeJob(new S3UploadJob(storage, file, fileName, contentType));


        if (fileName.toLowerCase().endsWith(".pdf")) {
            String epubFileName = fileName.substring(0, fileName.length()-4)+".epub";

            KoobeConverter converter;
            converter = converterService.getConverter(KoobeConverterType.PDF_TO_EPUB);

            UploadContextListener.executeJob(new ConverterJob(converter, file, storage, epubFileName, "application/epub+zip"));
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
            byte[] data = base64Encoding ? base64decoder.decode(chunk.getBytes()) : chunk.getBytes();
            stream.write(data);
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
