package com.koobe.editor.index.client.application.book.canvas;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by lyhcode on 2013/12/24.
 */
public interface BookCanvasBundle extends ClientBundle {
    public interface MyCss extends CssResource {
        String widgetMouseOver();
        String widgetMouseOut();
        String widgetMouseDown();
        String widget();
        String widgetHeading();
    }

    @Source("BookCanvasView.css")
    MyCss style();
}
