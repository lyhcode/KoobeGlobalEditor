package com.koobe.editor.widget.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by lyhcode on 2014/2/12.
 */
public class WidgetDragButton extends Label {

    private AbstractWidget widget;

    private Element element;

    public WidgetDragButton(AbstractWidget widget) {

        super("xxxxxxxxxx");

        this.widget = widget;
        this.element = this.getElement();

        initStyles();
}

    private void initStyles() {
        DOM.setStyleAttribute(element, "position", "absolute");
    }

    public void show() {
        RootPanel.get().add(this);

        DOM.setStyleAttribute(element, "left", (widget.getWidgetLeft() + widget.getWidgetWidth() / 2 - element.getClientWidth() / 2) + "px");
        DOM.setStyleAttribute(element, "top", (widget.getWidgetTop() + widget.getWidgetHeight() - element.getClientHeight() / 2) + "px");
    }

    public void hide() {
        RootPanel.get().remove(this);
    }
}
