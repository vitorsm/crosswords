package br.com.intcode.crosswords.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.intcode.crosswords.mapper.MapperFacade;
import br.com.intcode.crosswords.service.AuthenticateService;
import br.com.intcode.crosswords.service.QuestionService;
import br.com.intcode.crosswords.service.UserService;

@Service
@Scope("singleton")
public class CrosswordsFacade {

	@Autowired
	public AuthenticateService authenticateService;
	
	@Autowired
	public MapperFacade mf;
	
	@Autowired
	public QuestionService questionService;

	@Autowired
	public UserService userService;
}
