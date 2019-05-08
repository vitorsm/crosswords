package br.com.intcode.crosswords.service;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.intcode.crosswords.exceptions.BusinessRuleException;
import br.com.intcode.crosswords.exceptions.DAOException;
import br.com.intcode.crosswords.exceptions.PermissionException;
import br.com.intcode.crosswords.facade.CrosswordsFacade;
import br.com.intcode.crosswords.models.User;
import br.com.intcode.crosswords.repository.UserRepository;

@Service
public class UserService extends ServiceServer<User, Integer>{
	
	private static final Logger LOGGER = Logger.getLogger(UserService.class.toString());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CrosswordsFacade cf;
	
	@Override
	public void setRepository() {
		this.repository = userRepository;
	}

	@Override
	public void prepareToSave(User t, User user) {
		t.setCreatedAt(new Date(System.currentTimeMillis()));
		t.setCreatedBy(user);
	}

	@Override
	public void verifyToSave(User t, User currentAccount) throws PermissionException, DAOException {
		// TODO Ainda não existem configurações de permissão
	}

	@Override
	public void insert(User t) throws BusinessRuleException, PermissionException, DAOException {
		
		User currentUser = cf.authenticateService.currentUser();
		save(t, currentUser);
		
	}

	@Override
	public void update(User t) throws BusinessRuleException, PermissionException, DAOException {
		
		User currentUser = cf.authenticateService.currentUser();
		save(t, currentUser);
		
	}

	@Override
	public void delete(User t) throws BusinessRuleException, PermissionException, DAOException {
		
		this.repository.delete(t);
		
	}

}
