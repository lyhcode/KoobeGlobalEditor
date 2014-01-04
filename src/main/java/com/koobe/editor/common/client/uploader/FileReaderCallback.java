package com.koobe.editor.common.client.uploader;

/**
 * When chunks loaded by FileReaderJob,
 * methods in this callback will be invoked.
 */
public interface FileReaderCallback {
    void load(long index, String chunk);
    void progress(double progress);
    void complete();
    void error();
}
