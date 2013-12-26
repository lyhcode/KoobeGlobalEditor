package com.koobe.editor.common.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by lyhcode on 2013/12/23.
 */
public class LoadingIndicator extends Composite {
    interface Binder extends UiBinder<Widget, LoadingIndicator> {
    }

    private static Binder binder = GWT.create(Binder.class);

    public LoadingIndicator() {
        initWidget(binder.createAndBindUi(this));
    }

    @UiField
    HTMLPanel mainPanel;

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

        final LoadingIndicator _this = this;

        Timer timer = new Timer() {
            public void run() {
                _this.setVisible(false);
            }
        };

        timer.schedule(500);
    }
}
