package com.koobe.editor.common.client.uploader;

import org.vectomatic.file.File;

/**
 * When chunks loaded by FileReaderJob,
 * methods in this callback will be invoked.
 */
public interface FileReaderCallback {
    void load(File file, long index, BinaryString chunk);
    void progress(File file, double progress);
    void complete(File file);
    void error(File file);
}
