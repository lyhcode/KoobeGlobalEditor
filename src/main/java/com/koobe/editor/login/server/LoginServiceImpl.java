package com.koobe.editor.login.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.koobe.common.core.KoobeApplication;
import com.koobe.common.data.KoobeDataService;
import com.koobe.common.data.domain.User;
import com.koobe.common.data.repository.UserRepository;
import com.koobe.editor.login.client.LoginService;
import com.koobe.editor.login.shared.LoginResult;

import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements
        LoginService {

    KoobeApplication application = KoobeApplication.getInstance();
    KoobeDataService dataService = (KoobeDataService)application.getService(KoobeDataService.class);
    UserRepository userRepository = (UserRepository)dataService.getRepository(UserRepository.class);

    public LoginResult auth(String username, String password) throws IllegalArgumentException {

        List<User> users = userRepository.findByUserId(username);

        if (users.size() == 0) {
            return new LoginResult(false, "帳號或密碼錯誤。");
        }

        User user = users.get(0);

        if (password.equals(user.getPassword())) {
            return new LoginResult(true, "帳號及密碼驗證正確。");
        }

        return new LoginResult(false, "帳號或密碼錯誤");
    }
}
