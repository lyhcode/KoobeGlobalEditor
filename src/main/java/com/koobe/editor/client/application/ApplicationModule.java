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

package com.koobe.editor.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.koobe.editor.client.application.about.AboutPresenter;
import com.koobe.editor.client.application.about.AboutView;
import com.koobe.editor.client.application.contact.ContactPresenter;
import com.koobe.editor.client.application.contact.ContactPresenterBase;
import com.koobe.editor.client.application.contact.ContactView;
import com.koobe.editor.client.application.home.HomePresenter;
import com.koobe.editor.client.application.home.HomeView;
import com.koobe.editor.client.application.monitor.MonitorPresenter;
import com.koobe.editor.client.application.monitor.MonitorView;
import com.koobe.editor.client.application.sandbox.SandboxPresenter;
import com.koobe.editor.client.application.sandbox.SandboxView;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);

        bindPresenter(HomePresenter.class, HomePresenter.MyView.class, HomeView.class, HomePresenter.MyProxy.class);

        bindPresenter(AboutPresenter.class, AboutPresenter.MyView.class, AboutView.class,
                AboutPresenter.MyProxy.class);

        bindPresenter(ContactPresenter.class, ContactPresenterBase.MyView.class, ContactView.class,
                ContactPresenter.MyProxy.class);

        //monitor
        bindPresenter(MonitorPresenter.class, MonitorPresenter.MyView.class, MonitorView.class,
                MonitorPresenter.MyProxy.class);

        //sandbox
        bindPresenter(SandboxPresenter.class, SandboxPresenter.MyView.class, SandboxView.class,
                SandboxPresenter.MyProxy.class);
    }
}
