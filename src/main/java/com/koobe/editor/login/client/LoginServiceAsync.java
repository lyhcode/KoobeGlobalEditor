package com.koobe.editor.login.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.koobe.editor.login.shared.LoginResult;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface LoginServiceAsync {
    void auth(String username, String password, AsyncCallback<LoginResult> callback)
            throws IllegalArgumentException;
}
