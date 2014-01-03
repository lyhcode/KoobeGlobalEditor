package com.koobe.editor.index.client.application.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.typedarrays.client.Int8ArrayNative;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.typedarrays.shared.Int8Array;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.koobe.editor.admin.client.application.sandbox.SandboxUiHandlers;
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

import static com.google.gwt.query.client.GQuery.$;

public class UploadView extends ViewWithUiHandlers<UploadUiHandlers>
        implements UploadPresenter.MyView {

    @Override
    public void updateSendTextResult(String s) {
        debugHTML.setText(debugHTML.getText()+s);
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

    @UiField
    HTML debugHTML;

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
                        final File file = readQueue.get(0);
                        try {


                            //ArrayBuffer buffer = reader.getArrayBufferResult();
                            //Int8Array array = Int8ArrayNative.create(buffer);

                            //String url = "data:" + file.getType() + ";base64," + toBase64(array);
                            //debugHTML.setText(""+array.length());

                            //Window.alert("test");
                            //debugHTML.setText(reader.getStringResult());
                            //Window.alert(reader.getStringResult().length()+"");

                            //getUiHandlers().sendFile(reader.getStringResult());

                            String result = reader.getStringResult();

                            debugHTML.setText(result);

                            start += step;

                            long end = start + step;

                            if (end > file.getSize()) {
                                end = file.getSize();
                            }

                            if (start < file.getSize()) {

                                GWT.log(start + ", " + end);

                                reader.readAsBinaryString(file.slice(start, end));
                            }
                            else {
                                readQueue.remove(0);
                            }



                        } finally {
                            //readQueue.remove(0);
                            //readNextFile();
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

        boolean isMultipleFile = filesLength > 1;
        switchToFileUploaderPanel(isMultipleFile);

        if (filesLength == 1) {

            new FileUploadTask(files.getItem(0)).start();

            return;
        }

        for (File file : files) {
            readQueue.add(file);
        }

        readNextFile();
    }

    long start = 0;
    long step = 1024 * 100;

    private void readNextFile() {

        if (readQueue.size() > 0) {
            File file = readQueue.get(0);
            String type = file.getType();
            try {
                //reader.readAsArrayBuffer(file);

                GWT.log(""+file.getSize());


                reader.readAsBinaryString(file.slice(start, start + step));

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


    private static final char[] BASE64_CHARS = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', '+', '/'
    };

    private static final char BASE64_PADDING = '=';

    public static String toBase64(Int8Array array) {
        // Manual conversion to base64. There are probably smarter ways
        // to do this but the goal is to demonstrate typed arrays.
        StringBuilder builder = new StringBuilder();
        int length = array.length();
        if (length > 0) {
            char[] charArray = new char[4];
            int ix = 0;
            while (length >= 3) {
                int i = ((array.get(ix) & 0xff)<<16)
                        + ((array.get(ix+1) & 0xff)<<8)
                        + (array.get(ix+2) & 0xff);
                charArray[0] = BASE64_CHARS[i>>18];
                charArray[1] = BASE64_CHARS[(i>>12) & 0x3f];
                charArray[2] = BASE64_CHARS[(i>>6) & 0x3f];
                charArray[3] = BASE64_CHARS[i & 0x3f];
                builder.append(charArray);
                ix += 3;
                length -= 3;
            }
            if (length == 1) {
                int i = array.get(ix)&0xff;
                charArray[0] = BASE64_CHARS[i>>2];
                charArray[1] = BASE64_CHARS[(i<<4)&0x3f];
                charArray[2] = BASE64_PADDING;
                charArray[3] = BASE64_PADDING;
                builder.append(charArray);
            } else if (length == 2) {
                int i = ((array.get(ix) & 0xff)<<8)
                        + (array.get(ix+1) & 0xff);
                charArray[0] = BASE64_CHARS[i>>10];
                charArray[1] = BASE64_CHARS[(i>>4) & 0x3f];
                charArray[2] = BASE64_CHARS[(i<<2) & 0x3f];
                charArray[3] = BASE64_PADDING;
                builder.append(charArray);
            }
        }
        return builder.toString();
    }
}
