package com.koobe.editor.widget.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.RadioButton;
import org.gwtbootstrap3.client.ui.constants.Toggle;

/**
 * Title Widget for Chapter or Section Headers
 */
public class TitleWidget extends AbstractWidget {

    public enum SIZE {
        H1, H2, H3, H4, H5, H6;
    }

    private SIZE size;

    private String text;

    public TitleWidget(String text, SIZE size) {

        this.text = text;

        this.size = size;

        drawWidget();
    }

    private ButtonGroup buttons;

    @Override
    protected void initToolbar() {

        buttons = new ButtonGroup();
        buttons.setToggle(Toggle.BUTTONS);
        buttons.add(makeSizeButton("H1", SIZE.H1));
        buttons.add(makeSizeButton("H2", SIZE.H2));
        buttons.add(makeSizeButton("H3", SIZE.H3));
        buttons.add(makeSizeButton("H4", SIZE.H4));
        buttons.add(makeSizeButton("H5", SIZE.H5));
        buttons.add(makeSizeButton("H6", SIZE.H6));

        toolbar.add(buttons);
    }

    private void resetAllButtons() {
        if (buttons == null) return;

        int count = buttons.getWidgetCount();

        for (int i = 0; i < count; i++) {
            ((RadioButton)buttons.getWidget(i)).setActive(false);
        }
    }

    private RadioButton makeSizeButton(String label, final SIZE size) {
        final TitleWidget target = this;

        final RadioButton button = new RadioButton(label);
        button.setActive(this.size.equals(size));
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                resetAllButtons();
                button.setActive(true);
                target.setSize(size);
            }
        });
        return button;
    }

    @Override
    protected void drawWidget() {

        element = Document.get().createHElement(size.ordinal() + 1);
        element.setInnerText(text);
        element.setAttribute("contenteditable", "false");

        html.clear();
        html.getElement().appendChild(element);
    }

    @Override
    public void setEditable(boolean editable) {

        super.setEditable(editable);

        if (editable) {
            element.setAttribute("contenteditable", "true");
        }
        else {
            element.setAttribute("contenteditable", "false");
        }
    }

    public SIZE getSize() {
        return size;
    }

    public void setSize(SIZE size) {
        this.size = size;

        drawWidget();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
