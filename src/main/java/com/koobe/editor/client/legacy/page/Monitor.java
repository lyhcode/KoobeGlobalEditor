package com.koobe.editor.client.legacy.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.svenjacobs.gwtbootstrap3.client.ui.Row;

/**
 * @author Joshua Godi
 */
public class Monitor extends Composite {

    interface MonitorUiBinder extends UiBinder<Row, Monitor> {
    }

    private static MonitorUiBinder uiBinder = GWT.create(MonitorUiBinder.class);

    public Monitor() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
