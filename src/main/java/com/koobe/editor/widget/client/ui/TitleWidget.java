package com.koobe.editor.widget.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.RadioButton;
import org.gwtbootstrap3.client.ui.constants.Toggle;

/**
 * Title Widget for Chapter or Section Headers
 */
public class TitleWidget extends AbstractWidget {

    private SIZE size;

    private final static SIZE DEFAULT_SIZE = SIZE.H1;

    private String text;
    private ButtonGroup buttons;

    public TitleWidget(String text) {
        this(text, DEFAULT_SIZE);
    }

    public TitleWidget(String text, SIZE size) {

        this.text = text;

        this.size = size;

        drawWidget();
    }

    @Override
    protected void initToolbar() {

        buttons = new ButtonGroup();
        buttons.setName("sizeGroup");
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
        button.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                //resetAllButtons();
                //button.setActive(true);
                target.setSize(size);

                //event.stopPropagation();
                //event.preventDefault();
            }
        });
        return button;
    }

    @Override
    protected void drawWidget() {

        element = Document.get().createHElement(size.ordinal() + 1);
        element.setInnerText(text);

        html.update(element);
    }

    @Override
    public void setEditable(boolean editable) {

        super.setEditable(editable);

        if (editable) {
            element.setAttribute("contenteditable", "true");
        }
        else {
            element.removeAttribute("contenteditable");
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

    public enum SIZE {
        H1, H2, H3, H4, H5, H6;
    }
}
