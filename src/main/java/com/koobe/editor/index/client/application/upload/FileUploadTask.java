package com.koobe.editor.index.client.application.upload;

import com.google.gwt.core.client.GWT;
import org.vectomatic.file.File;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.events.*;

/**
 * Created by lyhcode on 2014/1/4.
 */
public class FileUploadTask {

    private FileReader reader;

    private File file;
    private long fileSize;

    private long start = 0;

    private final static int SLICE_SIZE = 102400;

    public FileUploadTask(File file) {
        this.file = file;
        this.fileSize = file.getSize();
        initReader();
    }

    private void initReader() {
        reader = new FileReader();
        reader.addLoadEndHandler(new LoadEndHandler() {
            @Override
            public void onLoadEnd(LoadEndEvent event) {
                if (reader.getError() == null) {
                    try {
                        String result = reader.getStringResult();

                        start += SLICE_SIZE + 1;

                        long end = start + SLICE_SIZE;

                        if (end > fileSize) {
                            end = fileSize;
                        }

                        if (fileSize - end < SLICE_SIZE) {
                            end = fileSize;
                        }

                        if (start <= fileSize) {

                            GWT.log(start + ", " + end);

                            reader.readAsBinaryString(file.slice(start, end));
                        }
                    } finally {
                    }
                }
            }
        });

        reader.addProgressHandler(new ProgressHandler() {
            @Override
            public void onProgress(ProgressEvent progressEvent) {
                GWT.log("progress: " + progressEvent.loaded());
            }
        });

        reader.addErrorHandler(new ErrorHandler() {
            @Override
            public void onError(ErrorEvent event) {
                GWT.log("read file error");
            }
        });
    }

    public void start() {
        reader.readAsBinaryString(file.slice(start, start + SLICE_SIZE));
    }
}
