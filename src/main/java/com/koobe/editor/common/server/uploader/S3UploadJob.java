package com.koobe.editor.common.server.uploader;

import com.koobe.common.storage.impl.KoobeStorage;

import java.io.File;

/**
 * S3 Upload Job
 */
public class S3UploadJob implements Runnable {

    private final KoobeStorage storage;
    private final File file;
    private final String s3path;
    private final String contentType;
    private final boolean autoDelete;

    public S3UploadJob(KoobeStorage storage, File file, String s3path, String contentType) {
        this.storage = storage;
        this.file = file;
        this.s3path = s3path;
        this.contentType = contentType;

        autoDelete = true;
    }

    @Override
    public void run() {

        System.out.println("upload s3 " + getS3path());

        if (getFile().exists() && getFile().isFile()) {
            getStorage().putFile(getS3path(), getFile());
        }

        if (isAutoDelete()) {
            getFile().delete();
        }
    }

    public KoobeStorage getStorage() {
        return storage;
    }

    public File getFile() {
        return file;
    }

    public String getS3path() {
        return s3path;
    }

    public String getContentType() {
        return contentType;
    }

    public boolean isAutoDelete() {
        return autoDelete;
    }
}
