package com.koobe.editor.widget.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;
import org.gwtbootstrap3.client.ui.Button;

/**
 * Created by lyhcode on 2014/1/28.
 */
public class BookChapter extends HTMLPanel {

    public BookChapter(String text) {

        super("<b>"+text+"</b>");

        DOM.setStyleAttribute(this.getElement(), "display", "inline-block");
        DOM.setStyleAttribute(this.getElement(), "float", "left");
        DOM.setStyleAttribute(this.getElement(), "width", "100px");
        DOM.setStyleAttribute(this.getElement(), "height", "100px");
        DOM.setStyleAttribute(this.getElement(), "border", "1px solid black");
        DOM.setStyleAttribute(this.getElement(), "background", "#fff");
        DOM.setStyleAttribute(this.getElement(), "margin", "5px");


    }
}
