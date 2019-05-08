package br.com.intcode.crosswords.service;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.intcode.crosswords.exceptions.BusinessRuleException;
import br.com.intcode.crosswords.exceptions.DAOException;
import br.com.intcode.crosswords.exceptions.PermissionException;
import br.com.intcode.crosswords.facade.CrosswordsFacade;
import br.com.intcode.crosswords.models.Question;
import br.com.intcode.crosswords.models.User;
import br.com.intcode.crosswords.repository.QuestionRepository;

@Service
public class QuestionService extends ServiceServer<Question, Integer>{

	private static final Logger LOGGER = Logger.getLogger(QuestionService.class.toString());
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private CrosswordsFacade cf;

	@Override
	public void setRepository() {
		this.repository = questionRepository;
	}

	@Override
	public void prepareToSave(Question t, User user) {
		t.setCreatedAt(new Date(System.currentTimeMillis()));
		t.setCreatedBy(user);
	}

	@Override
	public void verifyToSave(Question t, User currentAccount) throws PermissionException, DAOException {
		// TODO Ainda não possui controle de permissões
	}

	@Override
	public void insert(Question t) throws BusinessRuleException, PermissionException, DAOException {
		
		User currentUser = cf.authenticateService.currentUser();
		save(t, currentUser);
		
	}

	@Override
	public void update(Question t) throws BusinessRuleException, PermissionException, DAOException {
		
		User currentUser = cf.authenticateService.currentUser();
		save(t, currentUser);
		
	}

	@Override
	public void delete(Question t) throws BusinessRuleException, PermissionException, DAOException {
		
		this.repository.delete(t);
		
	}
	
	
}
