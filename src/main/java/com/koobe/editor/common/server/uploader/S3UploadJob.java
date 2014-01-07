package com.koobe.editor.common.server.uploader;

import com.koobe.common.storage.impl.KoobeStorage;

import java.io.File;

/**
 * Created by lyhcode on 2014/1/7.
 */
public class S3UploadJob implements Runnable {

    private final KoobeStorage storage;
    private final File file;
    private final String s3path;
    private final String contentType;

    public S3UploadJob(KoobeStorage storage, File file, String s3path, String contentType) {
        this.storage = storage;
        this.file = file;
        this.s3path = s3path;
        this.contentType = contentType;
    }

    @Override
    public void run() {

        System.out.println("upload s3 " + s3path);

        if (file.exists() && file.isFile()) {
            storage.putFile(s3path, file);
        }
    }
}
