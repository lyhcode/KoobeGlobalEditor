package com.koobe.editor.login.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.koobe.editor.login.shared.LoginResult;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.FormGroup;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ValidationState;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;

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
    Container container;

    @UiField
    Button loginButton;

    @UiField
    TextBox usernameTextBox;

    @UiField
    FormGroup usernameFormGroup;

    @UiField
    TextBox passwordTextBox;

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

        loginService.auth(username, password, new AsyncCallback<LoginResult>() {
            @Override
            public void onFailure(Throwable throwable) {
                Bootbox.alert("Request failed.");
            }

            @Override
            public void onSuccess(LoginResult result) {
                if (result.isSuccess()) {
                    changeValidationState(ValidationState.SUCCESS);

                    new Timer() {
                        @Override
                        public void run() {
                            String queryString = Window.Location.getQueryString();
                            Window.Location.assign("/" + queryString);
                        }
                    }.schedule(1500);

                    $("#page").as(UiEffects).effect("drop");


                } else {
                    changeValidationState(ValidationState.ERROR);

                    $("#loginbox").as(UiEffects).effect("shake");

                    Bootbox.alert(result.getMessage());
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
