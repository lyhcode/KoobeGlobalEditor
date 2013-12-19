package com.koobe.editor.client.legacy.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.svenjacobs.gwtbootstrap3.client.ui.Row;

/**
 * @author Joshua Godi
 */
public class Import extends Composite {

    interface ImportUiBinder extends UiBinder<Row, Import> {
    }

    private static ImportUiBinder uiBinder = GWT.create(ImportUiBinder.class);

    public Import() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
