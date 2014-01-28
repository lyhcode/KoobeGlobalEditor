package com.koobe.editor.widget.client.ui;

import com.google.gwt.dom.client.Document;

/**
 * Text Widget for Contents
 */
public class CodeWidget extends AbstractWidget {

    private String text;

    public CodeWidget(String text) {

        this.text = text;

        drawWidget();
    }

    @Override
    protected void initToolbar() {
    }

    @Override
    protected void drawWidget() {

        element = Document.get().createPreElement();
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
