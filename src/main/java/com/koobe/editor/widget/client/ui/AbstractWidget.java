package com.koobe.editor.widget.client.ui;

import com.allen_sauer.gwt.dnd.client.DragController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;

/**
 * Book Widget Abstract Class
 */
public abstract class AbstractWidget extends Composite {

    final WidgetBundle bundle = GWT.create(WidgetBundle.class);

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

    protected WidgetRemoveButton removeButton;

    protected WidgetDragButton dragButton;

    protected FocusPanel focusPanel;

    protected WidgetHTML html;

    protected boolean editable = false;

    protected Element element;

    private DragController dragController;

    public AbstractWidget() {

        bundle.style().ensureInjected();

        focusPanel = new FocusPanel();

        focusPanel.addStyleName(bundle.style().widget());

        focusPanel.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                setEditable(true);

                //event.preventDefault();
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
                focusPanel.addStyleName(bundle.style().widgetMouseOver());
            }
        });

        focusPanel.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                focusPanel.removeStyleName(bundle.style().widgetMouseOver());
            }
        });

        focusPanel.addDragStartHandler(new DragStartHandler() {
            @Override
            public void onDragStart(DragStartEvent dragStartEvent) {
                GWT.log("onDragStart");
            }
        });

        initWidget(focusPanel);

        focusPanel.add(html = new WidgetHTML());
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {

        if (isEditable() == editable) return;

        this.editable = editable;

        if (editable) {

            setActiveEditableWidget(this);

            focusPanel.addStyleName(bundle.style().widgetEditable());

            showToolbar();
            showRemoveButton();
            showDragButton();
        }
        else {
            focusPanel.removeStyleName(bundle.style().widgetEditable());

            hideToolbar();
            hideRemoveButton();
            hideDragButton();
        }
    }

    protected abstract void initToolbar();

    private void showToolbar() {
        toolbar = new WidgetToolbar(this);
        initToolbar();
        toolbar.show();
    }

    private void hideToolbar() {
        if (toolbar != null) {
            toolbar.hide();
        }
    }

    private void showRemoveButton() {
        removeButton = new WidgetRemoveButton(this);
        removeButton.setStyleName(bundle.style().widgetRemoveButton());
        removeButton.show();
    }

    private void hideRemoveButton() {
        if (removeButton != null) {
           removeButton.hide();
        }
    }

    private void showDragButton() {
        dragButton = new WidgetDragButton(this);
        dragButton.setStyleName(bundle.style().widgetDragButton());
        dragButton.show();

        if (dragController != null) {
            dragController.makeDraggable(this, dragButton);
        }
    }

    private void hideDragButton() {
        if (dragButton != null) {
            dragButton.hide();
        }

        if (dragController != null) {
            dragController.makeNotDraggable(this);
        }
    }

    protected abstract void drawWidget();

    protected void execCommand(String aCommandName, String aValueArgument) {
        execCommand(aCommandName, false, aValueArgument);
    }

    protected native void execCommand(String aCommandName, boolean aShowDefaultUI, String aValueArgument) /*-{
        $wnd.console.log("aCommandName = " + aCommandName);
        $wnd.console.log("aShowDefaultUI = " + aShowDefaultUI);
        $wnd.console.log("aValueArgument = " + aValueArgument);

        var result =
        //$wnd.document.execCommand(aCommandName, aShowDefaultUI, aValueArgument);
        $wnd.document.execCommand(aCommandName, false, aValueArgument);

        $wnd.console.log(result);
    }-*/;

    public int getWidgetLeft() {
        return focusPanel.getElement().getAbsoluteLeft();
    }

    public int getWidgetTop() {
        return focusPanel.getElement().getAbsoluteTop();
    }

    public int getWidgetWidth() {
        return focusPanel.getElement().getClientWidth();
    }

    public int getWidgetHeight() {
        return focusPanel.getElement().getClientHeight();
    }

    public String getHTML() {
        return html.getHTML();
    }

    public DragController getDragController() {
        return dragController;
    }

    public void setDragController(DragController dragController) {
        this.dragController = dragController;
    }

    public void scrollIntoView() {
        Window.scrollTo(0, getElement().getAbsoluteTop() - 150);
    }
}
