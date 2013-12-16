package com.koobe.editor.client.legacy.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.svenjacobs.gwtbootstrap3.client.ui.Row;

/**
 * @author Joshua Godi
 */
public class Default extends Composite {

    interface DefaultUiBinder extends UiBinder<Row, Default> {
    }

    private static DefaultUiBinder uiBinder = GWT.create(DefaultUiBinder.class);

    public Default() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
