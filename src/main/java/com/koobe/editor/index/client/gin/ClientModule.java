package com.koobe.editor.index.client.gin;

import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import com.koobe.editor.index.client.application.ApplicationModule;
import com.koobe.editor.index.client.place.NameTokens;

public class ClientModule extends AbstractPresenterModule {
    //private static final String ANALYTICS_ACCOUNT = "UA-8319339-6";

    @Override
    protected void configure() {
        install(new DefaultModule(DefaultPlaceManager.class));
        install(new DispatchAsyncModule());
        install(new ApplicationModule());

        // DefaultPlaceManager Places
        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.homePage);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.homePage);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.homePage);

        // Google Analytics
        //bindConstant().annotatedWith(GaAccount.class).to(ANALYTICS_ACCOUNT);
    }
}
