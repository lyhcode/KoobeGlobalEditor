package com.koobe.editor.widget.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.koobe.editor.editor.client.application.home.HomeBundle;

import static com.google.gwt.query.client.GQuery.$;

/**
 * Created by lyhcode on 2014/1/21.
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

    protected FocusPanel focusPanel;

    protected WidgetHTML html;

    protected boolean editable = false;

    protected Element element;

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
        }
        else {
            focusPanel.removeStyleName(bundle.style().widgetEditable());

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

        removeButton = new Anchor("×");

        removeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                targetWidget.setEditable(false);
                targetWidget.removeFromParent();
            }
        });

        removeButton.setStyleName(bundle.style().widgetRemoveButton());

        DOM.setStyleAttribute(removeButton.getElement(), "position", "relative");
        DOM.setStyleAttribute(removeButton.getElement(), "left", (focusPanel.getElement().getAbsoluteLeft() + focusPanel.getElement().getClientWidth() - 10) + "px");

        RootPanel.get().add(removeButton);

        DOM.setStyleAttribute(removeButton.getElement(), "top", (focusPanel.getElement().getAbsoluteTop() - removeButton.getElement().getClientHeight() - 45) + "px");
    }

    private void hideRemoveButton() {

        if (removeButton != null) {
            RootPanel.get().remove(removeButton);
        }
    }

    protected abstract void drawWidget();

    protected void execCommand(String aCommandName, String aValueArgument) {
        execCommand(aCommandName, false, aValueArgument);
    }

    protected native void execCommand(String aCommandName, Boolean aShowDefaultUI, String aValueArgument) /*-{
        $wnd.console.log(aCommandName);
        $wnd.console.log(aShowDefaultUI);
        $wnd.console.log(aValueArgument);

        var result =
        $wnd.document.execCommand(aCommandName, aShowDefaultUI, aValueArgument);

        $wnd.console.log(result);
    }-*/;

    public int getWidgetLeft() {
        return focusPanel.getElement().getAbsoluteLeft();
    }

    public int getWidgetTop() {
        return focusPanel.getElement().getAbsoluteTop();
    }

    public String getHTML() {
        return html.getHTML();
    }
}
