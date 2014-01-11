package com.koobe.editor.index.client.application.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.koobe.editor.common.client.uploader.FileReaderCallback;
import com.koobe.editor.common.client.uploader.FileReaderJob;
import com.koobe.editor.index.client.place.NameTokens;
import gwtquery.plugins.ui.UiWidget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ProgressBar;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.vectomatic.dnd.DataTransferExt;
import org.vectomatic.dnd.DropPanel;
import org.vectomatic.file.File;
import org.vectomatic.file.FileList;
import org.vectomatic.file.FileUploadExt;

import static com.google.gwt.query.client.GQuery.$;
import static com.google.gwt.query.client.GQuery.window;

public class UploadView extends ViewWithUiHandlers<UploadUiHandlers>
        implements UploadPresenter.MyView {

    private final static String SUPPORT_FILE_TYPE = ".doc,.docx,.txt,.ppt,.pptx,.pdf,.epub,.zip";
    private final static String[] SUPPORT_FILE_TYPE_LIST = SUPPORT_FILE_TYPE.split(",");

    @Override
    public void enableForwardButton() {
        forwardButton.setEnabled(true);
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

        $(customUpload).attr("accept", SUPPORT_FILE_TYPE);

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

    @UiField
    ProgressBar progressBar;

    @UiHandler("customUploadButton")
    public void onCustomUploadButtonClick(ClickEvent event) {
        customUpload.click();
    }

    @UiHandler("customUpload")
    public void onCustomUploadChange(ChangeEvent event) {
        getUiHandlers().processFiles(customUpload.getFiles());
    }

    @UiHandler("dropPanel")
    public void onDragOver(DragOverEvent event) {
        event.stopPropagation();
        event.preventDefault();
    }

    @UiHandler("dropPanel")
    public void onDragEnter(DragEnterEvent event) {

        dropPanel.addStyleName(res.style().dragOver());

        event.stopPropagation();
        event.preventDefault();
    }

    @UiHandler("dropPanel")
    public void onDragLeave(DragLeaveEvent event) {

        dropPanel.removeStyleName(res.style().dragOver());
        dropPanel.removeStyleName(res.style().dragOverError());

        event.stopPropagation();
        event.preventDefault();
    }

    @UiHandler("dropPanel")
    public void onDrop(DropEvent event) {

        FileList files = event.getDataTransfer().<DataTransferExt>cast().getFiles();

        if (!checkAllFileIsSupported(files)) {
            dropPanel.addStyleName(res.style().dragOverError());
            Bootbox.alert("包含不支援的檔案。");
        }
        else {
            getUiHandlers().processFiles(files);
        }

        event.stopPropagation();
        event.preventDefault();
    }

    @UiHandler("forwardButton")
    public void onForwardButtonClick(ClickEvent event) {
        forwardToBookList();
    }

    /**
     * Jump to: My Book List
     */
    private void forwardToBookList() {
        PlaceRequest responsePlaceRequest = new PlaceRequest.Builder()
                .nameToken(NameTokens.getBookListPage())
                .build();
        placeManager.revealPlace(responsePlaceRequest);
    }

    @Override
    public void switchToFileChooserPanel() {
        fileChooserPanel.setVisible(true);
        fileUploaderPanel.setVisible(false);
        multipleFileUploaderPanel.setVisible(false);
    }

    @Override
    public void switchToFileUploaderPanel(boolean multiple) {
        fileChooserPanel.setVisible(false);

        if (!multiple) {
            fileUploaderPanel.setVisible(true);
        }
        else {
            multipleFileUploaderPanel.setVisible(true);
        }
    }

    @Override
    public void updateSendTextResult(String s) {
        debugHTML.setText(s);
    }

    @Override
    public void updateProgress(double progress) {
        int percent = (int)(progress*100);

        progressBar.setPercent(percent);
        //progressBar.setText(percent + "%");
    }

    private boolean checkAllFileIsSupported(FileList files) {
        GWT.log(files.getLength()+".");
        for (File file : files) {

            String fileName = file.getName().toLowerCase();

            GWT.log(fileName);

            boolean supported = false;

            for (String type : SUPPORT_FILE_TYPE_LIST) {
                if (fileName.endsWith(type)) {
                    supported = true;
                    break;
                }
            }

            if (!supported) {
                return false;
            }
        }
        return true;
    }
}
