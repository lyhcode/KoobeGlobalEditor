package com.koobe.editor.widget.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HTML;

/**
 * Created by lyhcode on 2014/1/24.
 */
public class WidgetHTML extends HTML {

    public void updateElement(Element element) {
        this.getElement().appendChild(element);
    }
}
