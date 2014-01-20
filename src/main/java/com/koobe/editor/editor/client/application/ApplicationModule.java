package com.koobe.editor.editor.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.koobe.editor.editor.client.application.home.HomePresenter;
import com.koobe.editor.editor.client.application.home.HomeView;

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
    }
}
