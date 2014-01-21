package com.koobe.editor.editor.client.ui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;

/**
 * Created by lyhcode on 2014/1/21.
 */
public abstract class AbstractWidget extends Composite {

    private static AbstractWidget activeEditableWidget = null;

    protected  HorizontalPanel toolbar = new HorizontalPanel();

    protected FocusPanel focusPanel;

    protected HTML html;

    protected boolean editable = false;

    public AbstractWidget() {
        focusPanel = new FocusPanel();

        focusPanel.addStyleName("book-widget");

        focusPanel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                setEditable(true);
                
                event.preventDefault();
                event.stopPropagation();
            }
        });

        focusPanel.addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
            }
        });

        focusPanel.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                focusPanel.addStyleName("book-widget-mouse-over");
            }
        });

        focusPanel.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                focusPanel.removeStyleName("book-widget-mouse-over");
            }
        });

        initWidget(focusPanel);

        html = new HTML();

        focusPanel.add(html);

        initToolbar();
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {

        this.editable = editable;

        if (editable) {

            inactiveEditableWidget();

            activeEditableWidget = this;

            focusPanel.addStyleName("book-widget-editable");

            showToolbar();
        }
        else {
            focusPanel.removeStyleName("book-widget-editable");

            hideToolbar();
        }
    }

    public static void inactiveEditableWidget() {
        if (activeEditableWidget != null) {
            activeEditableWidget.setEditable(false);
        }
    }

    protected abstract void initToolbar();

    private void showToolbar() {

        //LayoutPanel layoutPanel = new LayoutPanel();
        DOM.setStyleAttribute(toolbar.getElement(), "position", "fixed");
        DOM.setStyleAttribute(toolbar.getElement(), "top", (focusPanel.getElement().getAbsoluteTop()-30)+"px");
        DOM.setStyleAttribute(toolbar.getElement(), "left", focusPanel.getElement().getAbsoluteLeft()+"px");
        DOM.setStyleAttribute(toolbar.getElement(), "height", "30px");
        //layoutPanel.add(toolbar);

        //lastLayoutPanel = layoutPanel;

        RootPanel.get().add(toolbar);
    }

    private void hideToolbar() {
        RootPanel.get().remove(toolbar);
    }

    protected abstract void drawWidget();

}
