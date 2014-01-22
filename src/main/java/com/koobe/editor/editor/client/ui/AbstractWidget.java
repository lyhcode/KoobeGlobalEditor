package com.koobe.editor.editor.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;

/**
 * Created by lyhcode on 2014/1/21.
 */
public abstract class AbstractWidget extends Composite {

    private static AbstractWidget activeEditableWidget = null;

    protected  HorizontalPanel toolbar;

    protected FocusPanel focusPanel;

    protected HTML html;

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

        html = new HTML();

        focusPanel.add(html);
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
            showRemoveButton();
        }
        else {
            focusPanel.removeStyleName("book-widget-editable");

            hideToolbar();
            hideRemoveButton();
        }
    }

    public static void inactiveEditableWidget() {
        if (activeEditableWidget != null) {
            activeEditableWidget.setEditable(false);
        }
    }

    protected abstract void initToolbar();

    private void showToolbar() {

        toolbar = new HorizontalPanel();

        initToolbar();

        //LayoutPanel layoutPanel = new LayoutPanel();
        DOM.setStyleAttribute(toolbar.getElement(), "position", "relative");
        DOM.setStyleAttribute(toolbar.getElement(), "left", focusPanel.getElement().getAbsoluteLeft()+"px");
        //layoutPanel.add(toolbar);

        RootPanel.get().add(toolbar);

        DOM.setStyleAttribute(toolbar.getElement(), "top", (focusPanel.getElement().getAbsoluteTop() - toolbar.getElement().getClientHeight()-5)+"px");
    }

    private void hideToolbar() {
        RootPanel.get().remove(toolbar);
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
}
