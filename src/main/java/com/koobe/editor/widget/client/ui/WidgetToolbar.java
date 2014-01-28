package com.koobe.editor.widget.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap3.client.ui.ButtonToolBar;

/**
 * Created by lyhcode on 2014/1/23.
 */
public class WidgetToolbar extends ButtonToolBar {

    private int widgetLeft;
    private int widgetTop;

    private Element element;

    public WidgetToolbar(int widgetLeft, int widgetTop) {
        this.widgetLeft = widgetLeft;
        this.widgetTop = widgetTop;

        this.element = this.getElement();

        DOM.setStyleAttribute(element, "position", "relative");
    }

    public void show() {
        RootPanel.get().add(this);

        int left = widgetLeft;
        int top = widgetTop - this.getElement().getClientHeight() - 5;

        moveTo(left, top);
    }

    public void hide() {
        RootPanel.get().remove(this);
    }

    public void moveTo(int left, int top) {
        DOM.setStyleAttribute(element, "left", left + "px");
        DOM.setStyleAttribute(element, "top", top + "px");
    }
}
