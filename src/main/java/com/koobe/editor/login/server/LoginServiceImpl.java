package com.koobe.editor.login.server;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koobe.common.data.domain.User;
import com.koobe.common.data.repository.UserRepository;
import com.koobe.common.gwt.server.servlet.GenericRemoteServiceServlet;
import com.koobe.editor.login.client.LoginService;
import com.koobe.editor.login.shared.LoginResult;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping("/login.rpc")
public class LoginServiceImpl extends GenericRemoteServiceServlet implements LoginService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
    UserRepository userRepository;
	
//	@PersistenceContext
//	EntityManager entityManager;

    public LoginResult auth(String username, String password) throws IllegalArgumentException {
    	
    	// userRepository = (UserRepository) koobeDataService.getRepository(UserRepository.class);
    	
        List<User> users = userRepository.findByUserId(username);

        if (users.size() == 0) {
        	log.info("error account or password");
            return new LoginResult(false, "帳號或密碼錯誤。");
        }

        User user = users.get(0);

        if (password.equals(user.getPassword())) {
            return new LoginResult(true, "帳號及密碼驗證正確。");
        }

        return new LoginResult(false, "帳號或密碼錯誤");
    }
}
