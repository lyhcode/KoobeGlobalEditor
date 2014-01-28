package com.koobe.editor.widget.client.ui;

import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SourceElement;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.RadioButton;
import org.gwtbootstrap3.client.ui.constants.Toggle;

/**
 * Title Widget for Chapter or Section Headers
 */
public class AudioWidget extends AbstractWidget {

    private String src;

    private ButtonGroup buttons;

    public AudioWidget(String src) {

        this.src = src;

        drawWidget();
    }

    @Override
    protected void initToolbar() {

        buttons = new ButtonGroup();
        buttons.setName("alignGroup");
        buttons.setToggle(Toggle.BUTTONS);


        toolbar.add(buttons);
    }

    private void resetAllButtons() {
        if (buttons == null) return;

        int count = buttons.getWidgetCount();

        for (int i = 0; i < count; i++) {
            ((RadioButton)buttons.getWidget(i)).setActive(false);
        }
    }

    @Override
    protected void drawWidget() {

        element = Document.get().createAudioElement();
        ((AudioElement)element).setSrc(src);

        element.setAttribute("controls", "");
        element.setAttribute("preload", "auto");

        SourceElement source = Document.get().createSourceElement();
        source.setSrc(src);

        element.appendChild(source);

        html.update(element);
    }

    @Override
    public void setEditable(boolean editable) {
        super.setEditable(editable);
    }
}
