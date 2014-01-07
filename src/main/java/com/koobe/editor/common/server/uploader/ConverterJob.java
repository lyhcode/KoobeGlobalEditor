package com.koobe.editor.common.server.uploader;

import com.koobe.common.converter.KoobeConverter;
import com.koobe.common.storage.impl.KoobeStorage;

import java.io.File;
import java.io.IOException;

/**
 * Created by lyhcode on 2014/1/7.
 */
public class ConverterJob implements Runnable {

    private KoobeConverter converter;
    private File src;
    private KoobeStorage storage;
    private String s3path;
    private String contentType;

    public ConverterJob(KoobeConverter converter, File src, KoobeStorage storage, String s3path, String contentType) {
        this.converter = converter;
        this.src = src;
        this.storage = storage;
        this.s3path = s3path;
        this.contentType = contentType;
    }

    @Override
    public void run() {

        File dest = null;

        try {
            String prefix = this.getClass().getSimpleName();
            String suffix = converter.getDestType().toString();

            dest = File.createTempFile(prefix, suffix);

            converter.convert(src, dest);

            if (dest.exists()) {
                storage.putFile(s3path, dest);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
