package com.koobe.editor.widget.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.constants.Toggle;

/**
 * Text Widget for Contents
 */
public class TextWidget extends AbstractWidget {

    private String text;

    public TextWidget(String text) {

        this.text = text;

        drawWidget();
    }

    @Override
    protected void initToolbar() {

        ButtonGroup buttons = new ButtonGroup();

        buttons.setToggle(Toggle.BUTTONS);

        Button bold = new Button("B");

        bold.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                execCommand("Bold", "false");
            }
        });

        toolbar.add(bold);

        toolbar.add(new Button("ITALIC"));

        toolbar.add(buttons);
    }

    @Override
    protected void drawWidget() {

        element = Document.get().createPElement();
        element.setInnerText(text);

        html.update(element);
    }

    @Override
    public void setEditable(boolean editable) {

        super.setEditable(editable);

        if (editable) {
            focusPanel.getElement().setAttribute("contenteditable", "true");
        }
        else {
            focusPanel.getElement().setAttribute("contenteditable", "false");
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
