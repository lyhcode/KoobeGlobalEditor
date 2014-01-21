package com.koobe.editor.editor.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.event.dom.client.*;
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
    protected void drawWidget() {
        panel.clear();

        ParagraphElement paragraphElement = Document.get().createPElement();
        paragraphElement.setInnerText(text);
        paragraphElement.setAttribute("contenteditable", "true");

        html = new HTML();
        html.getElement().appendChild(paragraphElement);

        panel.add(html);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
