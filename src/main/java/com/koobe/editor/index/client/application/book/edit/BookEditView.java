package com.koobe.editor.index.client.application.book.edit;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.koobe.common.data.domain.Draft;

public class BookEditView extends ViewImpl implements BookEditPresenter.MyView {

    interface Binder extends UiBinder<Widget, BookEditView> {
    }

    private final PlaceManager placeManager;

    @UiField
    ListBox draftCategory;

    @UiField
    ListBox draftSubCategory;

    @UiField
    ListBox draftRating;

    @UiField
    ListBox draftPrivacy;

    @Inject
    public BookEditView(Binder binder, PlaceManager placeManager) {
        this.placeManager = placeManager;

        initWidget(binder.createAndBindUi(this));

        initUiField();
    }

    private void initUiField() {
        draftCategory.addItem("文學");

        draftSubCategory.addItem("文學評論");
        draftSubCategory.addItem("中國古典文學");
        draftSubCategory.addItem("散文");

        draftRating.addItem("一般內容");
        draftRating.addItem("限制級內容");

        draftPrivacy.addItem("所有人");
        draftPrivacy.addItem("只限朋友");
        draftPrivacy.addItem("限群組");
        draftPrivacy.addItem("私人");
    }


}
