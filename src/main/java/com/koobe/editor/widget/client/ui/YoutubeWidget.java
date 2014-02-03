package com.koobe.editor.widget.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import org.gwtbootstrap3.client.ui.ButtonGroup;
import org.gwtbootstrap3.client.ui.RadioButton;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Toggle;

import static com.google.gwt.query.client.GQuery.$;

/**
 * Title Widget for Chapter or Section Headers
 */
public class YoutubeWidget extends AbstractWidget {

    private String videoId;

    private ButtonGroup buttons;

    public YoutubeWidget(String videoId) {

        this.videoId = videoId;

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
        final YoutubeWidget target = this;

        final RadioButton button = new RadioButton(label);

        button.setIcon(icon);

        //button.setActive(this.align.equals(align));

        button.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                //target.setAlign(align);
            }
        });
        return button;
    }

    @Override
    protected void drawWidget() {

        /**
         * <iframe width="560" height="315" src="//www.youtube.com/embed/ZKEaypYJbb4" frameborder="0" allowfullscreen></iframe>
         */
        element = Document.get().createIFrameElement();
        ((IFrameElement)element).setSrc("//www.youtube.com/embed/" + videoId);

        element.setAttribute("frameborder", "0");
        element.setClassName("youtube-video ");

        //$(element).css("width", "100%");

        html.update(element);

        $(element).css("width", "100%");

        new Timer() {
            @Override
            public void run() {
                $(element).height($(element).width() / 16 * 9);
            }
        }.schedule(3000);

        Window.addWindowScrollHandler(new Window.ScrollHandler() {
            @Override
            public void onWindowScroll(Window.ScrollEvent scrollEvent) {
                $(element).height($(element).width() / 16 * 9);
            }
        });
    }

    @Override
    public void setEditable(boolean editable) {
        super.setEditable(editable);
    }

    public enum ALIGN {
        LEFT, CENTER, RIGHT;
    }
}
