package com.koobe.editor.index.client.application.upload;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by lyhcode on 2013/12/24.
 */
public interface UploadBundle extends ClientBundle {
    public interface MyCss extends CssResource {
        String dragOver();
        String dragOverError();
    }

    @Source("BookCanvasView.css")
    MyCss style();
}
