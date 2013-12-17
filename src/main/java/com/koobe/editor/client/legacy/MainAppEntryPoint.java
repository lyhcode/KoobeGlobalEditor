package com.koobe.editor.client.legacy;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.koobe.editor.client.legacy.page.Default;
import com.koobe.editor.client.legacy.page.Import;
import com.koobe.editor.client.legacy.page.Monitor;
import com.koobe.editor.client.login.LoginService;
import com.koobe.editor.client.login.LoginServiceAsync;
import com.svenjacobs.gwtbootstrap3.client.ui.ListItem;
import com.svenjacobs.gwtbootstrap3.client.ui.NavbarBrand;
import com.svenjacobs.gwtbootstrap3.client.ui.Row;

/**
 * GWT Main Entry Point
 *
 * @author lyhcode
 */
public class MainAppEntryPoint implements EntryPoint, HistoryListener {

    interface DemoEntryPointUiBinder extends UiBinder<Widget, MainAppEntryPoint> {
    }

    private static DemoEntryPointUiBinder uiBinder = GWT.create(DemoEntryPointUiBinder.class);

    @UiField
    Row container;
    @UiField
    NavbarBrand brand;
    @UiField
    ListItem importLink;
    @UiField
    ListItem logoutLink;

    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    /**
     * Create a remote service proxy to talk to the server-side Greeting
     * service.
     */
    private final LoginServiceAsync greetingService = GWT.create(LoginService.class);

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {

        History.addHistoryListener(this);

        RootPanel.get().add(uiBinder.createAndBindUi(this));

        //processFormContainer();

        loadPage(History.getToken());
    }

    @UiHandler("brand")
    void handleBrandClick(ClickEvent e) {
        //Window.Location.reload();
        String queryString = Window.Location.getQueryString();
        Window.Location.assign("/index.html" + queryString);
        e.preventDefault();
    }

    @UiHandler("logoutLink")
    void handleLogoutLinkClick(ClickEvent e) {
        String queryString = Window.Location.getQueryString();
        Window.Location.assign("/login.html" + queryString);
        e.preventDefault();
    }

    @UiHandler("monitorLink")
    void handleMonitorLinkClick(ClickEvent e) {
        loadPage("page-Monitor");
        e.preventDefault();
    }

    @UiHandler("importLink")
    void handleImportLinkClick(ClickEvent e) {
        loadPage("page-Import");
        e.preventDefault();
    }

    /**
     * Load page to container
     *
     * @param pageName
     */
    private void loadPage(String pageName) {

        Class pageClass = findPageClass(pageName);

        if (pageClass == null) {
            GWT.log("Page not found");
            return;
        }

        Widget pageWidget = GWT.create(pageClass);

        container.clear();
        container.add(pageWidget);

        // New record in browser history
        History.newItem(pageName);
    }

    private Class findPageClass(String pageName) {
        if (pageName == null) {
            return null;
        }

        if (pageName.isEmpty()) {
            return Default.class;
        }
        if (pageName.endsWith("Monitor")) {
            return Monitor.class;
        }
        if (pageName.endsWith("Import")) {
            return Import.class;
        }

        return null;
    }

    @Override
    public void onHistoryChanged(String historyToken) {
        loadPage(historyToken);
    }
}
