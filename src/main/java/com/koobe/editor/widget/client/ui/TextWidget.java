package com.koobe.editor.widget.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.koobe.editor.widget.client.helper.SelectionHelper;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.DropDownMenu;
import org.gwtbootstrap3.client.ui.ListItem;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Toggle;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;
import org.gwtbootstrap3.extras.bootbox.client.callback.PromptCallback;

/**
 * Text Widget for Contents
 */
public class TextWidget extends AbstractWidget {

    final static private String COLORS[] = {
        "#000000", "#424242", "#636363", "#9C9C94", "#CEC6CE", "#EFEFEF", "#EFF7F7", "#FFFFFF",
        "#FF0000", "#FF9C00", "#FFFF00", "#00FF00", "#00FFFF", "#0000FF", "#9C00FF", "#FF00FF",
        "#F7C6CE", "#FFE7CE", "#FFEFC6", "#D6EFD6", "#CEDEE7", "#CEE7F7", "#D6D6E7", "#E7D6DE",
        "#E79C9C", "#FFC69C", "#FFE79C", "#B5D6A5", "#A5C6CE", "#9CC6EF", "#B5A5D6", "#D6A5BD",
        "#E76363", "#F7AD6B", "#FFD663", "#94BD7B", "#73A5AD", "#6BADDE", "#8C7BC6", "#C67BA5",
        "#CE0000", "#E79439", "#EFC631", "#6BA54A", "#4A7B8C", "#3984C6", "#634AA5", "#A54A7B",
        "#9C0000", "#B56308", "#BD9400", "#397B21", "#104A5A", "#085294", "#311873", "#731842",
        "#630000", "#7B3900", "#846300", "#295218", "#083139", "#003163", "#21104A", "#4A1031"
    };

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

    /**
     * Make a dropdown button group for color palette picker
     * @param label
     * @param command
     * @return
     */
    private ButtonGroup makeColorDropDownButtonGroup(String label, final String command) {
        ButtonGroup buttonGroup = new ButtonGroup();
        Button fg = new Button();
        fg.setToggle(Toggle.DROPDOWN);
        fg.setText(label);
        buttonGroup.add(fg);

        DropDownMenu colors = new DropDownMenu();
        for (final String colorCode : COLORS) {
            ListItem item = new ListItem("");
            item.getElement().getFirstChildElement().setInnerHTML("<span style=\"color:"+colorCode+";background-color:"+colorCode+"\">◼</span>");
            DOM.setStyleAttribute(item.getElement(), "display", "inline-block");
            item.getElement().getFirstChildElement().setClassName(bundle.style().btnColor());

            item.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    execCommand(command, colorCode);

                }
            });
            colors.add(item);
        }
        buttonGroup.add(colors);

        return buttonGroup;
    }
    @Override
    protected void initToolbar() {

        ButtonGroup colorButtons = new ButtonGroup();

        colorButtons.add(makeColorDropDownButtonGroup("FG", "forecolor"));
        colorButtons.add(makeColorDropDownButtonGroup("BG", "backcolor"));

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

    private Button makeCommandButton(String label, String command, AskForValue askForValue) {
        return makeCommandButton(null, label, command, askForValue);
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
