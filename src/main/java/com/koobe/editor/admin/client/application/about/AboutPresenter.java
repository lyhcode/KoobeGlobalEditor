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

package com.koobe.editor.admin.client.application.about;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.koobe.editor.admin.client.application.ApplicationPresenter;
import com.koobe.editor.admin.client.place.NameTokens;

public class AboutPresenter extends Presenter<AboutPresenter.MyView, AboutPresenter.MyProxy> {
    /**
     * {@link AboutPresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(NameTokens.aboutPage)
    public interface MyProxy extends ProxyPlace<AboutPresenter> {
    }

    /**
     * {@link AboutPresenter}'s view.
     */
    public interface MyView extends View {
    }

    @Inject
    AboutPresenter(EventBus eventBus,
                   MyView view,
                   MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
    }
}
