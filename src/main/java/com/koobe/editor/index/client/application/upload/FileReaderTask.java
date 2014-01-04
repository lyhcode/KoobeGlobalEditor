package com.koobe.editor.index.client.application.upload;

import com.google.gwt.core.client.GWT;
import org.vectomatic.file.File;
import org.vectomatic.file.FileReader;
import org.vectomatic.file.events.*;

/**
 * Created by lyhcode on 2014/1/4.
 */
public class FileReaderTask {

    private FileReader reader;

    private File file;
    private long fileSize;

    private long start = 0;

    /**
     * Adjust this value for suitable network transfer rate
     */
    private final static int SLICE_SIZE = 102400;

    private FileReaderCallback callback;

    public FileReaderTask(File file, FileReaderCallback callback) {
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
                            callback.upload(b64encode(chunk));
                        }

                        if (start <= fileSize) {

                            long end = start + SLICE_SIZE;

                            if (end > fileSize) {
                                end = fileSize;
                            }

                            if (fileSize - end < SLICE_SIZE) {
                                end = fileSize;
                            }

                            GWT.log(start + ", " + end);

                            reader.readAsBinaryString(file.slice(start, end));

                            start = end + 1;
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
                GWT.log("progress: " + progressEvent.loaded());
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
        start = SLICE_SIZE + 1;
        reader.readAsBinaryString(file.slice(0, SLICE_SIZE));
    }

    private static native String b64decode(String a) /*-{
      return $wnd.atob(a);
    }-*/;

    private static native String b64encode(String a) /*-{
      return $wnd.btoa(a);
    }-*/;
}
