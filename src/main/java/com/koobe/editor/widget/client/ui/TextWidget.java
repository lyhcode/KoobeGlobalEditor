package com.koobe.editor.widget.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.koobe.editor.widget.client.helper.SelectionHelper;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.bootbox.client.callback.PromptCallback;

/**
 * Text Widget for Contents
 */
public class TextWidget extends AbstractWidget {

    class AskForValue {

        private String prompt;

        AskForValue(String prompt) {
            this.prompt = prompt;
        }

        public void ask(PromptCallback callback) {
            Bootbox.prompt(prompt, callback);
        }

        public String ask() {
            return Window.prompt(prompt, "");
        }
    }

    private String text;

    public TextWidget(String text) {

        this.text = text;

        drawWidget();
    }

    @Override
    protected void initToolbar() {

        ButtonGroup colorButtons = new ButtonGroup();

        colorButtons.add(makeCommandButton(IconType.FONT, "forecolor", new AskForValue("Foreground Color Code?")));
        colorButtons.add(makeCommandButton(IconType.FONT, "backcolor", new AskForValue("Background Color Code?")));

        toolbar.add(colorButtons);


        ButtonGroup basicButtons = new ButtonGroup();

        /*-- must enable clipboard permissions in user.js
        buttons.add(makeCommandButton(IconType.SCISSORS, "cut", "false"));
        buttons.add(makeCommandButton(IconType.CLIPBOARD, "paste", "false"));
        */

        basicButtons.add(makeCommandButton(IconType.BOLD, "bold", "false"));
        basicButtons.add(makeCommandButton(IconType.ITALIC, "italic", "false"));
        basicButtons.add(makeCommandButton(IconType.UNDERLINE, "underline", "false"));
        basicButtons.add(makeCommandButton(IconType.LINK, "createlink", new AskForValue("URL?")));
        basicButtons.add(makeCommandButton(IconType.CHAIN_BROKEN, "unlink", "false"));

        toolbar.add(basicButtons);

        ButtonGroup alignButtons = new ButtonGroup();

        alignButtons.add(makeCommandButton(IconType.ALIGN_LEFT, "justifyleft", "false"));
        alignButtons.add(makeCommandButton(IconType.ALIGN_CENTER, "justifycenter", "false"));
        alignButtons.add(makeCommandButton(IconType.ALIGN_RIGHT, "justifyright", "false"));
        alignButtons.add(makeCommandButton(IconType.ALIGN_JUSTIFY, "justifyfull", "false"));

        toolbar.add(alignButtons);

        ButtonGroup listButtons = new ButtonGroup();

        listButtons.add(makeCommandButton(IconType.LIST_UL, "insertUnorderedList", "false"));
        listButtons.add(makeCommandButton(IconType.LIST_OL, "insertOrderedList", "false"));

        toolbar.add(listButtons);

        ButtonGroup controlButtons = new ButtonGroup();

        controlButtons.add(makeCommandButton(IconType.UNDO, "undo", "false"));

        toolbar.add(controlButtons);
    }

    private Button makeCommandButton(IconType icon, String command, AskForValue askForValue) {
        return makeCommandButton(icon, "", command, askForValue);
    }

    private Button makeCommandButton(IconType icon, String label, final String command, final AskForValue askForValue) {
        Button button = new Button(label);

        if (icon != null) {
            button.setIcon(icon);
        }

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                final SelectionHelper selectionHelper;
                selectionHelper = new SelectionHelper();
                selectionHelper.saveRange();

                //execCommand(command, askForValue.ask());
                askForValue.ask(new PromptCallback() {
                    @Override
                    public void callback(final String value) {


                        GWT.log(command);
                        GWT.log(value);

                        selectionHelper.restoreRange();

                        execCommand(command, value);

                    }
                });
            }
        });

        return button;
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

        element = Document.get().createPElement();
        element.setInnerText(text);

        html.update(element);
    }

    @Override
    public void setEditable(boolean editable) {

        super.setEditable(editable);

        if (editable) {
            html.getElement().setAttribute("contenteditable", "true");
        }
        else {
            html.getElement().removeAttribute("contenteditable");
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
