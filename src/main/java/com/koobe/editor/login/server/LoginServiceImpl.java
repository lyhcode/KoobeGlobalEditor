package com.koobe.editor.login.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.koobe.editor.login.client.LoginService;

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
