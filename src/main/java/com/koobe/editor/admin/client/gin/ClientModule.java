/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.koobe.editor.admin.client.gin;

import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import com.koobe.editor.admin.client.application.ApplicationModule;
import com.koobe.editor.admin.client.place.NameTokens;

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
