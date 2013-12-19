package com.koobe.editor.server.login;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.koobe.editor.client.login.LoginService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
        LoginService {

    public String auth(String username, String password) throws IllegalArgumentException {
        return "ok";
    }
}
