package com.koobe.editor.widget.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import static com.google.gwt.query.client.GQuery.$;

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

        element = Document.get().createDivElement();
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
