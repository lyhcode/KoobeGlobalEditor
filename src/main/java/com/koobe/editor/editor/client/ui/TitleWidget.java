package com.koobe.editor.editor.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.Iterator;

import static com.google.gwt.query.client.GQuery.$;

/**
 * Created by lyhcode on 2014/1/21.
 */
public class TitleWidget extends AbstractWidget {

    public enum SIZE {
        H1, H2, H3, H4, H5, H6;
    }

    private SIZE size;

    private String text;

    public TitleWidget(String text) {

        this.text = text;

        this.size = SIZE.H1;

        drawWidget();
    }

    @Override
    protected void initToolbar() {
        toolbar.add(makeSizeButton("H1", SIZE.H1));
        toolbar.add(makeSizeButton("H2", SIZE.H2));
        toolbar.add(makeSizeButton("H3", SIZE.H3));
        toolbar.add(makeSizeButton("H4", SIZE.H4));
        toolbar.add(makeSizeButton("H5", SIZE.H5));
        toolbar.add(makeSizeButton("H6", SIZE.H6));
    }

    private Button makeSizeButton(String label, final SIZE size) {
        final TitleWidget target = this;

        Button button = new Button(label);
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                target.setSize(size);
            }
        });
        return button;
    }

    @Override
    protected void drawWidget() {

        HeadingElement element = Document.get().createHElement(size.ordinal() + 1);
        element.setInnerText(text);
        element.setAttribute("contenteditable", "true");

        html.setHTML("");
        html.getElement().appendChild(element);
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
