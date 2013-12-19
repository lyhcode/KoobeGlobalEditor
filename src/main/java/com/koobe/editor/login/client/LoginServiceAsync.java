package com.koobe.editor.login.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface LoginServiceAsync {
    void auth(String username, String password, AsyncCallback<String> callback)
            throws IllegalArgumentException;
}
