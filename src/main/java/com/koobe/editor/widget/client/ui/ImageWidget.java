package com.koobe.editor.widget.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.RadioButton;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Toggle;

import static com.google.gwt.query.client.GQuery.$;

/**
 * Title Widget for Chapter or Section Headers
 */
public class ImageWidget extends AbstractWidget {

    private String src;

    private ALIGN align;

    private ButtonGroup buttons;

    public ImageWidget(String src, ALIGN align) {

        this.src = src;

        this.align = align;

        drawWidget();
    }

    @Override
    protected void initToolbar() {

        buttons = new ButtonGroup();
        buttons.setName("alignGroup");
        buttons.setToggle(Toggle.BUTTONS);
        buttons.add(makeAlignButton(IconType.ALIGN_LEFT, "", ALIGN.LEFT));
        buttons.add(makeAlignButton(IconType.ALIGN_CENTER, "", ALIGN.CENTER));
        buttons.add(makeAlignButton(IconType.ALIGN_RIGHT, "", ALIGN.RIGHT));
        toolbar.add(buttons);
    }

    private void resetAllButtons() {
        if (buttons == null) return;

        int count = buttons.getWidgetCount();

        for (int i = 0; i < count; i++) {
            ((RadioButton)buttons.getWidget(i)).setActive(false);
        }
    }

    private RadioButton makeAlignButton(IconType icon, String label, final ALIGN align) {
        final ImageWidget target = this;

        final RadioButton button = new RadioButton(label);

        button.setIcon(icon);

        button.setActive(this.align.equals(align));

        button.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                target.setAlign(align);
            }
        });
        return button;
    }

    @Override
    protected void drawWidget() {

        element = Document.get().createImageElement();
        ((ImageElement)element).setSrc(src);

        $(element).css("width", "50%");

        html.update(element);

        $(html).css("text-align", align.toString().toLowerCase());
    }

    @Override
    public void setEditable(boolean editable) {
        super.setEditable(editable);
    }

    public void setAlign(ALIGN align) {
        this.align = align;

        drawWidget();
    }

    public enum ALIGN {
        LEFT, CENTER, RIGHT;
    }
}
