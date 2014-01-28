package com.koobe.editor.widget.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.constants.IconType;

/**
 * Text Widget for Contents
 */
public class QuoteWidget extends AbstractWidget {

    private String text;

    public QuoteWidget(String text) {

        this.text = text;

        drawWidget();
    }

    @Override
    protected void initToolbar() {

        ButtonGroup basicButtons = new ButtonGroup();

        /*-- must enable clipboard permissions in user.js
        buttons.add(makeCommandButton(IconType.SCISSORS, "cut", "false"));
        buttons.add(makeCommandButton(IconType.CLIPBOARD, "paste", "false"));
        */

        basicButtons.add(makeCommandButton(IconType.BOLD, "bold", "false"));
        basicButtons.add(makeCommandButton(IconType.ITALIC, "italic", "false"));
        basicButtons.add(makeCommandButton(IconType.UNDERLINE, "underline", "false"));

        toolbar.add(basicButtons);
    }

    private Button makeCommandButton(IconType icon, String command, String param) {
        return makeCommandButton(icon, "", command, param);
    }

    private Button makeCommandButton(String label, String command, String param) {
        return makeCommandButton(null, label, command, param);
    }

    private Button makeCommandButton(IconType icon, String label, final String command, final String param) {
        Button button = new Button(label);

        if (icon != null) {
            button.setIcon(icon);
        }

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                execCommand(command, param);
            }
        });

        return button;
    }

    @Override
    protected void drawWidget() {

        html.getElement().setAttribute("spellcheck", "false");

        element = Document.get().createBlockQuoteElement();
        element.setInnerHTML("<p>" + text + "</p>");

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
