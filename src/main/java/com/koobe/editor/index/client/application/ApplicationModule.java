package com.koobe.editor.index.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.koobe.editor.index.client.application.book.canvas.BookCanvasPresenter;
import com.koobe.editor.index.client.application.book.canvas.BookCanvasView;
import com.koobe.editor.index.client.application.book.edit.BookEditPresenter;
import com.koobe.editor.index.client.application.book.edit.BookEditView;
import com.koobe.editor.index.client.application.book.list.BookListPresenter;
import com.koobe.editor.index.client.application.book.list.BookListView;
import com.koobe.editor.index.client.application.converter.status.StatusPresenter;
import com.koobe.editor.index.client.application.converter.status.StatusView;
import com.koobe.editor.index.client.application.home.HomePresenter;
import com.koobe.editor.index.client.application.home.HomeView;
import com.koobe.editor.index.client.application.upload.UploadPresenter;
import com.koobe.editor.index.client.application.upload.UploadView;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(
                ApplicationPresenter.class,
                ApplicationPresenter.MyView.class,
                ApplicationView.class,
                ApplicationPresenter.MyProxy.class);

        // Home
        bindPresenter(HomePresenter.class,
                HomePresenter.MyView.class,
                HomeView.class,
                HomePresenter.MyProxy.class);

        // Upload
        bindPresenter(UploadPresenter.class,
                UploadPresenter.MyView.class,
                UploadView.class,
                UploadPresenter.MyProxy.class);

        // Converter Status
        bindPresenter(StatusPresenter.class,
                StatusPresenter.MyView.class,
                StatusView.class,
                StatusPresenter.MyProxy.class);

        // Book List
        bindPresenter(BookListPresenter.class,
                BookListPresenter.MyView.class,
                BookListView.class,
                BookListPresenter.MyProxy.class);

        // Book Edit
        bindPresenter(BookEditPresenter.class,
                BookEditPresenter.MyView.class,
                BookEditView.class,
                BookEditPresenter.MyProxy.class);

        // Book Canvas
        bindPresenter(BookCanvasPresenter.class,
                BookCanvasPresenter.MyView.class,
                BookCanvasView.class,
                BookCanvasPresenter.MyProxy.class);

    }
}
