package com.koobe.editor.editor.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RichTextArea;

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
        Button bold = new Button("B");

        bold.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

            }
        });

        toolbar.add(bold);

        toolbar.add(new Button("ITALIC"));
    }

    @Override
    protected void drawWidget() {

        element = Document.get().createPElement();
        element.setInnerText(text);
        element.setAttribute("contenteditable", "false");

        html.setHTML("");
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
