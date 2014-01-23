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

package com.koobe.editor.admin.client.application.monitor;

import static com.google.gwt.query.client.GQuery.$;

import org.gwtbootstrap3.client.ui.Button;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.koobe.common.gwt.demo.service.client.MyDemoService;
import com.koobe.common.gwt.demo.service.client.MyDemoServiceAsync;
import com.koobe.common.model.enums.FileTypeEnum;
import com.koobe.common.model.message.ConvertEpubRequest;
import com.koobe.common.model.message.ExtractEpubRequest;

public class MonitorView extends ViewImpl implements MonitorPresenter.MyView {
    interface Binder extends UiBinder<Widget, MonitorView> {
    }

    @Inject
    public MonitorView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    @UiField
    Button refreshButton;
    
    MyDemoServiceAsync service = GWT.create(MyDemoService.class);

    @UiHandler("refreshButton")
    void onRefreshButtonClick(ClickEvent event) {
        //Window.alert("rest");

//        $("#monitor-table-body").append("<tr><td>123</td></tr>");
    	
//    	Book book = new Book();
//    	book.setBucket("aaaa");
        
        service.getExtractEpubRequest(new AsyncCallback<ExtractEpubRequest>() {
			@Override
			public void onSuccess(ExtractEpubRequest result) {
				String value = result.getAction() + result.getDestBucket() + result.getDestPath() + 
						result.getSrcBucket() + result.getSrcPath();
				$("#monitor-table-body").append("<tr><td>"+value+"</td></tr>");
			}
			
			@Override
			public void onFailure(Throwable caught) {
				$("#monitor-table-body").append("<tr><td>"+caught.getMessage()+"</td></tr>");
			}
		});
        
        service.getConvertEpubRequest(new AsyncCallback<ConvertEpubRequest>() {

			@Override
			public void onFailure(Throwable caught) {
				$("#monitor-table-body").append("<tr><td>"+caught.getMessage()+"</td></tr>");
			}

			@Override
			public void onSuccess(ConvertEpubRequest result) {
				String value = result.getDestFileType().toString() + result.getAction() + result.getSrcPath();
				FileTypeEnum t = result.getSrcFileType();
				$("#monitor-table-body").append("<tr><td>"+value+"</td></tr>" + "<tr><td>"+t.toString()+"</td></tr>");
			}
		});
    }
}
