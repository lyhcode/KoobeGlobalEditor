package com.koobe.editor.widget.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;

/**
 * Created by lyhcode on 2014/1/21.
 */
public abstract class AbstractWidget extends Composite {

    private static AbstractWidget activeEditableWidget = null;

    public static void setActiveEditableWidget(AbstractWidget widget) {

        if (activeEditableWidget != null && activeEditableWidget != widget) {
            activeEditableWidget.setEditable(false);
        }

        activeEditableWidget = widget;
    }

    public static AbstractWidget getActiveEditableWidget() {
        return activeEditableWidget;
    }

    protected WidgetToolbar toolbar;

    protected FocusPanel focusPanel;

    protected HTMLPanel html;

    protected boolean editable = false;

    protected Element element;

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

        html = new HTMLPanel("");

        focusPanel.add(html);
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        GWT.log("setEditable(" + editable + ")");

        // just ignore if status not change
        GWT.log(isEditable() + "," + editable);
        //if (isEditable() == editable) return;

        this.editable = editable;

        if (editable) {

            setActiveEditableWidget(this);

            focusPanel.addStyleName("book-widget-editable");

            showToolbar();
            showRemoveButton();
        }
        else {
            focusPanel.removeStyleName("book-widget-editable");

            hideToolbar();
            hideRemoveButton();
        }
    }

    protected abstract void initToolbar();

    private void showToolbar() {

        toolbar = new WidgetToolbar(getWidgetLeft(), getWidgetTop());

        initToolbar();

        toolbar.show();
    }

    private void hideToolbar() {
        if (toolbar != null) {
            toolbar.hide();
        }
    }

    private Anchor removeButton;

    private void showRemoveButton() {

        final AbstractWidget targetWidget = this;

        removeButton = new Anchor("x");

        removeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                targetWidget.setEditable(false);
                targetWidget.removeFromParent();
            }
        });

        removeButton.setStyleName("book-widget-remove-button");

        DOM.setStyleAttribute(removeButton.getElement(), "position", "relative");
        DOM.setStyleAttribute(removeButton.getElement(), "left", (focusPanel.getElement().getAbsoluteLeft() + focusPanel.getElement().getClientWidth()) + "px");

        RootPanel.get().add(removeButton);

        DOM.setStyleAttribute(removeButton.getElement(), "top", (focusPanel.getElement().getAbsoluteTop() - removeButton.getElement().getClientHeight()) + "px");

    }

    private void hideRemoveButton() {

        if (removeButton != null) {
            RootPanel.get().remove(removeButton);
        }
    }

    protected abstract void drawWidget();

    native void execCommand(String cmd, String param) /*-{
        $wnd.document.execCommand(cmd, false, param);
    }-*/;

    public int getWidgetLeft() {
        return focusPanel.getElement().getAbsoluteLeft();
    }

    public int getWidgetTop() {
        return focusPanel.getElement().getAbsoluteTop();
    }


}
