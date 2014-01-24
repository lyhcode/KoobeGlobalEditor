package com.koobe.editor.login.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.koobe.editor.login.shared.LoginResult;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("../login.rpc")
public interface LoginService extends RemoteService {
    LoginResult auth(String username, String password) throws IllegalArgumentException;
}
