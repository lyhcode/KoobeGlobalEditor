package com.koobe.editor.widget.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.HTML;

/**
 * Created by lyhcode on 2014/1/24.
 */
public class WidgetHTML extends HTML {

    public WidgetHTML() {
        setStyleName("widget");
    }

    public void update(Element element) {
        clear();
        getElement().appendChild(element);
    }

    public void clear() {
        int count = getElement().getChildCount();

        for (int i = 0; i < count; i++) {
            getElement().getChild(i).removeFromParent();
        }
    }
}
