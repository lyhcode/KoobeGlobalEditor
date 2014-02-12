package com.koobe.editor.widget.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import org.gwtbootstrap3.client.ui.ButtonToolBar;

/**
 * Created by lyhcode on 2014/1/23.
 */
public class WidgetToolbar extends ButtonToolBar {

    private AbstractWidget widget;

    private Element element;

    public WidgetToolbar(AbstractWidget widget) {
        this.widget = widget;
        this.element = this.getElement();

        initStyles();
    }

    private void initStyles() {
        DOM.setStyleAttribute(element, "display", "inline-block");
        DOM.setStyleAttribute(element, "position", "absolute");
    }

    public void show() {
        RootPanel.get().add(this);

        int left = widget.getWidgetLeft();
        int top = widget.getWidgetTop() - element.getClientHeight() - 5;

        moveTo(left, top);
    }

    public void hide() {
        RootPanel.get().remove(this);
    }

    private void moveTo(int left, int top) {
        DOM.setStyleAttribute(element, "left", left + "px");
        DOM.setStyleAttribute(element, "top", top + "px");
    }
}
