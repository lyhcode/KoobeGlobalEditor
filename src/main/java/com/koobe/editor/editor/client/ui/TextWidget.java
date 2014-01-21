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
 * Created by lyhcode on 2014/1/21.
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

        ParagraphElement element = Document.get().createPElement();
        element.setInnerText(text);
        element.setAttribute("contenteditable", "true");

        html.getElement().appendChild(element);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
