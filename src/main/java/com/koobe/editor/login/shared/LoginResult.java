package com.koobe.editor.login.shared;

import java.io.Serializable;

/**
 * Login Result
 */
public class LoginResult implements Serializable {

    private boolean isSuccess;

    private String message;

    public LoginResult() {

    }

    public LoginResult(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
