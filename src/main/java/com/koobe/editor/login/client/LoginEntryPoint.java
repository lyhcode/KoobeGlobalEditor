package com.koobe.editor.login.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.svenjacobs.gwtbootstrap3.bootbox.client.Bootbox;
import com.svenjacobs.gwtbootstrap3.client.ui.Button;
import com.svenjacobs.gwtbootstrap3.client.ui.FormGroup;
import com.svenjacobs.gwtbootstrap3.client.ui.PasswordTextBox;
import com.svenjacobs.gwtbootstrap3.client.ui.TextBox;
import com.svenjacobs.gwtbootstrap3.client.ui.constants.ValidationState;

import static com.google.gwt.query.client.GQuery.$;
import static gwtquery.plugins.ui.UiEffects.UiEffects;

/**
 * GWT Entry Point - Login
 *
 * @author lyhcode
 */
public class LoginEntryPoint implements EntryPoint {

    private final LoginServiceAsync loginService = GWT.create(LoginService.class);

    interface Binder extends UiBinder<Widget, LoginEntryPoint> {
    }

    private static Binder uiBinder = GWT.create(Binder.class);

    @UiField
    Button loginButton;
    @UiField
    TextBox usernameTextBox;
    @UiField
    FormGroup usernameFormGroup;
    @UiField
    PasswordTextBox passwordTextBox;
    @UiField
    FormGroup passwordFormGroup;

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        RootPanel.get().add(uiBinder.createAndBindUi(this));
    }

    @UiHandler("usernameTextBox")
    void onUsernameTextBoxKeyUp(KeyUpEvent keyUpEvent) {
        changeValidationState(null);
    }

    @UiHandler("passwordTextBox")
    void onPasswordTextBoxKeyUp(KeyUpEvent keyUpEvent) {
        changeValidationState(null);
    }

    @UiHandler("loginButton")
    void handleLoginButton(ClickEvent e) {

        String username = usernameTextBox.getText();
        String password = passwordTextBox.getText();

        loginService.auth(username, password, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable throwable) {
                Bootbox.alert("Request failed.");
            }

            @Override
            public void onSuccess(String result) {
                if ("ok1".equals(result)) {
                    changeValidationState(ValidationState.SUCCESS);

                    String queryString = Window.Location.getQueryString();
                    Window.Location.assign("/" + queryString);
                } else {
                    changeValidationState(ValidationState.ERROR);

                    $("#loginbox").as(UiEffects).effect("shake");
                }
            }
        });

        e.preventDefault();
    }

    private void changeValidationState(ValidationState state) {
        usernameFormGroup.setValidationState(state);
        passwordFormGroup.setValidationState(state);
    }
}
