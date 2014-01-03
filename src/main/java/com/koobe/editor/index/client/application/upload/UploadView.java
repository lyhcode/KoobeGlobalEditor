package com.koobe.editor.index.client.application.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.koobe.editor.index.client.place.NameTokens;
import org.gwtbootstrap3.client.ui.Button;
import org.vectomatic.dnd.DataTransferExt;
import org.vectomatic.dnd.DropPanel;
import org.vectomatic.file.FileList;
import org.vectomatic.file.FileUploadExt;

public class UploadView extends ViewWithUiHandlers<UploadUiHandlers>
        implements UploadPresenter.MyView {

    @Override
    public void updateSendTextResult(String s) {
        debugHTML.setText(s);
    }

    interface Binder extends UiBinder<Widget, UploadView> {
    }

    //@UiField(provided = true)
    final UploadBundle res = GWT.create(UploadBundle.class);

    private final PlaceManager placeManager;

    @Inject
    public UploadView(Binder binder, PlaceManager placeManager) {

        this.placeManager = placeManager;

        res.style().ensureInjected();
        initWidget(binder.createAndBindUi(this));

        forwardButton.setEnabled(false);
        multipleForwardButton.setEnabled(false);

        fileUploaderPanel.setVisible(false);
        multipleFileUploaderPanel.setVisible(false);
    }

    @UiField
    DropPanel dropPanel;

    @UiField
    Button customUploadButton;

    @UiField
    Button forwardButton;

    @UiField
    Button multipleForwardButton;

    @UiField
    FileUploadExt customUpload;

    @UiField
    HTMLPanel fileChooserPanel;

    @UiField
    HTMLPanel fileUploaderPanel;

    @UiField
    HTML fileUploaderInfoHTML;

    @UiField
    HTMLPanel multipleFileUploaderPanel;

    @UiField
    HTML debugHTML;

    @UiHandler("customUploadButton")
    public void onCustomUploadButtonClick(ClickEvent event) {
        customUpload.click();
    }

    @UiHandler("customUpload")
    public void onCustomUploadChange(ChangeEvent event) {
        processFiles(customUpload.getFiles());
    }

    @UiHandler("dropPanel")
    public void onDragOver(DragOverEvent event) {

        dropPanel.addStyleName(res.style().dragOver());

        event.stopPropagation();
        event.preventDefault();
    }

    @UiHandler("dropPanel")
    public void onDragEnter(DragEnterEvent event) {
        event.stopPropagation();
        event.preventDefault();
    }

    @UiHandler("dropPanel")
    public void onDragLeave(DragLeaveEvent event) {

        dropPanel.removeStyleName(res.style().dragOver());

        event.stopPropagation();
        event.preventDefault();
    }

    @UiHandler("dropPanel")
    public void onDrop(DropEvent event) {

        processFiles(event.getDataTransfer().<DataTransferExt>cast().getFiles());

        event.stopPropagation();
        event.preventDefault();
    }

    @UiHandler("forwardButton")
    public void onForwardButtonClick(ClickEvent event) {
        PlaceRequest responsePlaceRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.getConverterStatusPage())
                .build();
        placeManager.revealPlace(responsePlaceRequest);
    }

    private void switchToFileChooserPanel() {
        fileChooserPanel.setVisible(true);
        fileUploaderPanel.setVisible(false);
        multipleFileUploaderPanel.setVisible(false);
    }

    private void switchToFileUploaderPanel(boolean multiple) {
        fileChooserPanel.setVisible(false);

        if (!multiple) {
            fileUploaderPanel.setVisible(true);
        }
        else {
            multipleFileUploaderPanel.setVisible(true);
        }
    }

    /**
     * Adds a collection of file the queue and begin processing them
     *
     * @param files The file to process
     */
    private void processFiles(FileList files) {

        int filesLength = files.getLength();

        boolean isMultipleFile = filesLength > 1;
        switchToFileUploaderPanel(isMultipleFile);

        if (filesLength == 1) {

            new FileReaderTask(files.getItem(0), new FileReaderCallback() {
                @Override
                public void upload(String chunk) {
                    getUiHandlers().sendFileChunk(chunk);
                }

                @Override
                public void progress(int percent) {

                }

                @Override
                public void complete() {
                    GWT.log("file upload done");
                }

                @Override
                public void error() {
                    GWT.log("file upload error");
                }
            }).start();

            return;
        }


    }
}
