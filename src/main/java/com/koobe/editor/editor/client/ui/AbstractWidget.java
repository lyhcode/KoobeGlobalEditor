package com.koobe.editor.editor.client.ui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lyhcode on 2014/1/21.
 */
public abstract class AbstractWidget extends Composite {

    private static AbstractWidget activeEditableWidgets = null;

    protected FocusPanel panel;

    protected HTML html;

    protected boolean editable = false;

    public AbstractWidget() {
        panel = new FocusPanel();

        panel.addStyleName("book-widget");

        panel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                setEditable(true);
            }
        });

        panel.addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
            }
        });

        panel.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                panel.addStyleName("book-widget-mouse-over");
            }
        });

        panel.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                panel.removeStyleName("book-widget-mouse-over");
            }
        });

        initWidget(panel);
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {

        this.editable = editable;

        if (editable) {

            if (activeEditableWidgets != null) {
                activeEditableWidgets.setEditable(false);
            }

            activeEditableWidgets = this;

            panel.addStyleName("book-widget-editable");
        }
        else {
            panel.removeStyleName("book-widget-editable");
        }
    }



    protected abstract void drawWidget();

}
