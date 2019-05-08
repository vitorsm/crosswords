package br.com.intcode.crosswords.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.intcode.crosswords.exceptions.BusinessRuleException;
import br.com.intcode.crosswords.exceptions.DAOException;
import br.com.intcode.crosswords.exceptions.PermissionException;
import br.com.intcode.crosswords.facade.CrosswordsFacade;
import br.com.intcode.crosswords.models.User;
import br.com.intcode.crosswords.repository.UserRepository;
import br.com.intcode.crosswords.security.MD5PasswordEncoder;


@Service
public class AuthenticateService implements AuthenticationManager {

	private static final Logger LOGGER = Logger.getLogger(AuthenticateService.class.toString());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CrosswordsFacade cf;
	
	@Autowired
	MD5PasswordEncoder md5;
	
	private boolean auth(User user) {
		String login = user.getLogin();
		String password = user.getPassword();
		
		User userDb = userRepository.findByLoginAndPassword(login, password);
		user.setPassword(null);
		
		if (userDb != null) {
			user.setName(userDb.getName());
			return true;
		}
		
		return false;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String password = "";
		
		if (authentication.getCredentials() != null) {
			password = md5.encode(authentication.getCredentials().toString());
			
			User user = (User) authentication.getPrincipal();
			user.setPassword(password);
			
			if (auth(user)) {
				return authentication;
			}
		}
		
		throw new AuthenticationCredentialsNotFoundException("Bad credentials");
	}
	
	public User currentUser() throws DAOException {
		String subject = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		try {
			return cf.userService.findById(Integer.parseInt(subject));
		} catch (NumberFormatException | PermissionException | BusinessRuleException e) {
			e.printStackTrace();
			return null;
		}
	}
}
