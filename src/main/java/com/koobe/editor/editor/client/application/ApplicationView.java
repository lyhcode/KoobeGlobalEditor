package com.koobe.editor.editor.client.application;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.koobe.editor.common.client.ui.LoadingIndicator;
import org.gwtbootstrap3.client.ui.Container;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    Container container;

    @UiField
    LoadingIndicator loading;

    @Inject
    ApplicationView(Binder binder) {
        initWidget(binder.createAndBindUi(this));

        initUiField();
    }

    private void initUiField() {
        //footer.add(new HTML("Copyright &copy; KOOBE Inc."));
        //Window.alert(TestEnum.A.toString());
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == ApplicationPresenter.TYPE_SetMainContent) {
            container.clear();
            container.add(content);
        } else {
            super.setInSlot(slot, content);
        }
    }

    @Override
    public void showLoading(boolean visible) {
        if (visible) {
            loading.show();
        }
        else {
            loading.hide();
        }
    }
}
