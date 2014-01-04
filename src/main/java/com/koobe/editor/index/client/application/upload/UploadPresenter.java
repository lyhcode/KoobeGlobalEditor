/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.koobe.editor.index.client.application.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.xhr.client.XMLHttpRequest;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.koobe.editor.common.client.uploader.FileReaderCallback;
import com.koobe.editor.common.client.uploader.FileReaderJob;
import com.koobe.editor.common.client.uploader.UploadService;
import com.koobe.editor.common.client.uploader.UploadServiceAsync;
import com.koobe.editor.index.client.application.ApplicationPresenter;
import com.koobe.editor.index.client.place.NameTokens;
import org.vectomatic.file.File;
import org.vectomatic.file.FileList;

public class UploadPresenter extends Presenter<UploadPresenter.MyView, UploadPresenter.MyProxy>
        implements UploadUiHandlers {

    private final UploadServiceAsync uploadService = GWT.create(UploadService.class);

    private final DispatchAsync dispatcher;

    @ProxyCodeSplit
    @NameToken(NameTokens.uploadPage)
    public interface MyProxy extends ProxyPlace<UploadPresenter> {
    }

    public interface MyView extends View, HasUiHandlers<UploadUiHandlers> {
        void updateSendTextResult(String s);
        void updateProgress(double progress);
    }

    @Inject
    public UploadPresenter(EventBus eventBus,
                           MyView view,
                           MyProxy proxy,
                           DispatchAsync dispatcher) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

        this.dispatcher = dispatcher;

        getView().setUiHandlers(this);
    }

    /**
     * Adds a collection of file the queue and begin processing them
     *
     * @param files The list of files to process
     */
    public  void processFiles(FileList files) {

        int filesLength = files.getLength();

        if (filesLength > 1) {
            Window.alert("Oops, multiple file uploads not support yet.");
            return;
        }

        if (filesLength == 1) {

            File file = files.getItem(0);


            // Check file suffix first.

            if (!checkFileSuffix(file)) {
                Window.alert("Not allow this file type. " + file.getName());
                return;
            }

            // Check file size.

            if (!checkFileSize(file)) {
                Window.alert("File is too big. " + file.getName());
                return;
            }

            new FileReaderJob(file, new FileReaderCallback() {
                @Override
                public void load(long index, String chunk) {
                    uploadChunk(index, chunk);
                }

                @Override
                public void progress(double progress) {
                    getView().updateProgress(progress);
                }

                @Override
                public void complete() {
                    getView().updateProgress(1);
                }

                @Override
                public void error() {
                    GWT.log("file upload error");
                }
            }).start();
        }
    }

    private static final String[] ALLOW_FILE_SUFFIX = {".doc", ".docx", ".epub", ".txt", "pdf"};

    private static final long ALLOW_FILE_SIZE = 200 * 1024 * 1024; // Max 200MB

    /**
     * Check file suffix name
     * @param file
     * @return true if file suffix name is allow
     */
    private boolean checkFileSuffix(File file) {
        if (file == null) {
            return false;
        }

        String lowerCaseFileName = file.getName().toLowerCase();

        for (String ext : ALLOW_FILE_SUFFIX) {
            if (lowerCaseFileName.contains(ext)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check file size
     * @param file
     * @return true if file size is allow
     */
    private boolean checkFileSize(File file) {
        if (file == null) {
            return false;
        }

        if (file.getSize() <= ALLOW_FILE_SIZE) {
            return true;
        }

        return false;
    }

    private void uploadChunk(long index, String chunk) {

        /*uploadService.uploadChunk(index, chunk, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(String result) {
                //nothing
            }
        });*/

    }
}
