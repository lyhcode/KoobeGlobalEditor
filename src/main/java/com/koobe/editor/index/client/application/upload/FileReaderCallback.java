package com.koobe.editor.index.client.application.upload;

/**
 * Created by lyhcode on 2014/1/4.
 */
public interface FileReaderCallback {
    void upload(String chunk);
    void progress(int percent);
    void complete();
    void error();
}
