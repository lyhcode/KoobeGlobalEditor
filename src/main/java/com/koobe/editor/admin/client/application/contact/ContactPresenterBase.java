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

package com.koobe.editor.admin.client.application.contact;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * The base class of {@link ContactPresenter}. The goal of this class is just to show that {@code @ProxyEvent} can be
 * used in base classes. See Issue 180.
 *
 * @param <Proxy_> The {@link com.gwtplatform.mvp.client.proxy.Proxy} type.
 */
public abstract class ContactPresenterBase<Proxy_ extends Proxy<?>> extends
        Presenter<ContactPresenterBase.MyView, Proxy_> {

    String navigationHistory = "";

    /**
     * {@link ContactPresenter}'s view.
     */
    public interface MyView extends View {
        void setNavigationHistory(String navigationHistory);
    }

    ContactPresenterBase(EventBus eventBus,
                         MyView view,
                         Proxy_ proxy,
                         GwtEvent.Type<RevealContentHandler<?>> slot) {
        super(eventBus, view, proxy, slot);
    }

    /**
     * We keep track of the previously visited pages.
     *
     * @param event The {@link com.gwtplatform.mvp.client.proxy.LockInteractionEvent}.
     */
    @ProxyEvent
    public void onNavigation(NavigationEvent event) {
        if (navigationHistory.length() > 0) {
            navigationHistory += ", ";
        }
        navigationHistory += event.getRequest().getNameToken();
        getView().setNavigationHistory(navigationHistory);
    }
}
