package br.com.intcode.crosswords.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.intcode.crosswords.dto.UserDTO;
import br.com.intcode.crosswords.exceptions.BusinessRuleException;
import br.com.intcode.crosswords.exceptions.DAOException;
import br.com.intcode.crosswords.exceptions.PermissionException;
import br.com.intcode.crosswords.facade.CrosswordsFacade;
import br.com.intcode.crosswords.models.User;


@RestController
@RequestMapping(value = "serv/user")
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class.toString());
	
	@Autowired
	private CrosswordsFacade cf;
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> insert(UserDTO user) throws BusinessRuleException, PermissionException, DAOException {
		
		user.setId(0);
		User userObj = cf.mf.userMapper.mapToObj(user);
		cf.userService.insert(userObj);
		
		return new ResponseEntity<UserDTO>(cf.mf.userMapper.mapToDto(userObj), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> update(UserDTO user) throws PermissionException, BusinessRuleException, DAOException {
		
		User userObj = cf.mf.userMapper.mapToObj(user);
		cf.userService.update(userObj);
		
		return new ResponseEntity<UserDTO>(cf.mf.userMapper.mapToDto(userObj), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") int userId) throws BusinessRuleException, PermissionException, DAOException {
		
		if (userId <= 0)
			throw new BusinessRuleException("O id informado não é válido");
		
		User user = cf.userService.findById(userId);
		cf.userService.delete(user);
		
		return new ResponseEntity(HttpStatus.OK);
	}
}
