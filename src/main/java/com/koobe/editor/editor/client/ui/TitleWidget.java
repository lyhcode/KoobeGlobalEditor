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
    protected void drawWidget() {
        panel.clear();

        HeadingElement headingElement = Document.get().createHElement(1);
        headingElement.setInnerText(text);
        headingElement.setAttribute("contenteditable", "true");

        html = new HTML();
        html.getElement().appendChild(headingElement);

        panel.add(html);
    }

    public SIZE getSize() {
        return size;
    }

    public void setSize(SIZE size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
