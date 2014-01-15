package com.koobe.editor.index.client.application.book.canvas;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.koobe.editor.index.client.application.ApplicationPresenter;
import com.koobe.editor.index.client.place.NameTokens;

public class BookCanvasPresenter extends Presenter<BookCanvasPresenter.MyView, BookCanvasPresenter.MyProxy> {

    @ProxyCodeSplit
    @NameToken(NameTokens.bookCanvasPage)
    public interface MyProxy extends ProxyPlace<BookCanvasPresenter> {
    }

    public interface MyView extends View {
    }

    @Inject
    public BookCanvasPresenter(EventBus eventBus,
                               MyView view,
                               MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
    }
}
