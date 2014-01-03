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
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.koobe.editor.index.client.place.NameTokens;
import org.gwtbootstrap3.client.ui.Button;
import org.vectomatic.dnd.DataTransferExt;
import org.vectomatic.dnd.DropPanel;
import org.vectomatic.file.*;
import org.vectomatic.file.events.ErrorEvent;
import org.vectomatic.file.events.ErrorHandler;
import org.vectomatic.file.events.LoadEndEvent;
import org.vectomatic.file.events.LoadEndHandler;

import java.util.ArrayList;
import java.util.List;

public class UploadView extends ViewImpl implements UploadPresenter.MyView {

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

        initFileReader();
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

    protected FileReader reader = new FileReader();

    protected List<File> readQueue = new ArrayList<File>();

    /**
     * Initialize FileReader
     */
    private void initFileReader() {
        reader.addLoadEndHandler(new LoadEndHandler() {
            @Override
            public void onLoadEnd(LoadEndEvent event) {
                if (reader.getError() == null) {
                    if (readQueue.size() > 0) {
                        File file = readQueue.get(0);
                        try {
                            //imagePanel.add(createThumbnail(file));
                        } finally {
                            readQueue.remove(0);
                            readNextFile();
                        }
                    }
                }
            }
        });
        reader.addErrorHandler(new ErrorHandler() {
            @Override
            public void onError(ErrorEvent event) {
                if (readQueue.size() > 0) {
                    File file = readQueue.get(0);
                    handleError(file);
                    readQueue.remove(0);
                    readNextFile();
                }
            }
        });
    }

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

        switchToFileUploaderPanel(filesLength > 1);

        if (filesLength == 1) {
            File file1 = files.getItem(0);

            //StringBuffer html = new StringBuffer();
            //html.append(String.format("<code>%s</code>", file1.getName()));
            //html.append("<br/>");
            //html.append(CommonStringUtils.prettySize(file1.getSize()));

            //fileUploaderInfoHTML.setHTML(html.toString());
        }

        for (File file : files) {
            readQueue.add(file);
        }

        readNextFile();
    }

    private void readNextFile() {

        if (readQueue.size() > 0) {
            File file = readQueue.get(0);
            String type = file.getType();
            try {
                if ("image/svg+xml".equals(type)) {
                    reader.readAsText(file);
                } else if (type.startsWith("image/png")) {
                    readQueue.remove(0);
                    readNextFile();
                } else if (type.startsWith("image/")) {
                    //reader.readAsArrayBuffer(file);
                    reader.readAsBinaryString(file);

                } else if (type.startsWith("text/")) {
                    Blob blob = file;
                    if (file.getSize() > 0) {
                        blob = file.slice(0, 1000, "text/plain; charset=utf-8");
                    }
                    reader.readAsText(blob);
                }
            } catch (Throwable t) {
                handleError(file);
                readQueue.remove(0);
                readNextFile();
            }
        }
    }

    private void handleError(File file) {
        FileError error = reader.getError();
        String errorDesc = "";
        if (error != null) {
            ErrorCode errorCode = error.getCode();
            if (errorCode != null) {
                errorDesc = ": " + errorCode.name();
            }
        }
        Window.alert("File loading error for file: " + file.getName() + "\n" + errorDesc);
    }
}
