package com.koobe.editor.widget.client.ui;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by lyhcode on 2013/12/24.
 */
public interface WidgetBundle extends ClientBundle {
    public interface MyCss extends CssResource {
        String widget();
        String widgetMouseOver();
        String widgetEditable();
        String widgetRemoveButton();
    }

    @Source("WidgetBundle.css")
    MyCss style();
}
