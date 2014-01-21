package com.koobe.editor.editor.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Authoring Tool Sidebar
 */
public class Sidebar extends Composite {
    interface Binder extends UiBinder<Widget, Sidebar> {
    }

    private static Binder binder = GWT.create(Binder.class);

    public Sidebar() {
        initWidget(binder.createAndBindUi(this));
    }

    /**
     * Show Loading Indicator
     */
    public void show() {
        setVisible(true);
    }

    /**
     * Hide Loading Indicator
     */
    public void hide() {

        final Sidebar _this = this;

        Timer timer = new Timer() {
            public void run() {
                _this.setVisible(false);
            }
        };

        timer.schedule(500);
    }
}
