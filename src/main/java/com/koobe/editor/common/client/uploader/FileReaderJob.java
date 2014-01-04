package com.koobe.editor.common.client.uploader;

import com.google.gwt.core.client.GWT;
import org.vectomatic.file.File;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.events.*;

/**
 * Use this FileReaderJob to get chunks from File
 */
public class FileReaderJob {

    private FileReader reader;

    private File file;
    private long fileSize;

    private long index = 0;
    private long offset = 0;

    /**
     * Specify the size limit of each chunk (1024kb)
     */
    private final static int CHUNK_SIZE = 1024 * 1024;

    private FileReaderCallback callback;

    public FileReaderJob(File file, FileReaderCallback callback) {
        this.file = file;
        this.fileSize = file.getSize();
        this.callback = callback;
        initReader();
    }

    private void initReader() {
        reader = new FileReader();
        reader.addLoadEndHandler(new LoadEndHandler() {
            @Override
            public void onLoadEnd(LoadEndEvent event) {
            if (reader.getError() == null) {
                try {
                    String chunk = reader.getStringResult();

                    if (callback != null) {
                        //callback.load(index++, b64encode(chunk));
                        callback.load(index++, (chunk));
                        callback.progress(getProgress());
                    }

                    if (offset <= fileSize) {

                        long end = offset + CHUNK_SIZE;

                        if (end > fileSize) {
                            end = fileSize;
                        }

                        //if (fileSize - end < CHUNK_SIZE) {
                        //    end = fileSize;
                        //  }

                        reader.readAsBinaryString(file.slice(offset, end));

                        offset = end + 1;
                    }
                    else {
                        callback.complete();
                    }
                } finally {
                    //nothing
                }
            }
            }
        });

        reader.addProgressHandler(new ProgressHandler() {
            @Override
            public void onProgress(ProgressEvent progressEvent) {
            //GWT.log("progress: " + progressEvent.loaded());
            }
        });

        reader.addErrorHandler(new ErrorHandler() {
            @Override
            public void onError(ErrorEvent event) {
                callback.error();
            }
        });
    }

    public void start() {
        offset = CHUNK_SIZE + 1;
        reader.readAsBinaryString(file.slice(0, CHUNK_SIZE));
    }

    public long getOffset() {
        return offset;
    }

    public double getProgress() {
        return (double)offset / (double)fileSize;
    }

    private static native String b64encode(String a) /*-{
      return $wnd.btoa(a);
    }-*/;
}
