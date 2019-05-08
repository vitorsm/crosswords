package br.com.intcode.crosswords.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.intcode.crosswords.dto.QuestionDTO;
import br.com.intcode.crosswords.exceptions.BusinessRuleException;
import br.com.intcode.crosswords.exceptions.DAOException;
import br.com.intcode.crosswords.exceptions.PermissionException;
import br.com.intcode.crosswords.facade.CrosswordsFacade;
import br.com.intcode.crosswords.models.Question;

@RestController
@RequestMapping(value = "serv/user")
public class QuestionController {

private static final Logger LOGGER = Logger.getLogger(QuestionController.class.toString());
	
	@Autowired
	private CrosswordsFacade cf;
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuestionDTO> insert(QuestionDTO question) throws BusinessRuleException, PermissionException, DAOException {
		
		question.setId(0);
		Question questionObj = cf.mf.questionMapper.mapToObj(question);
		cf.questionService.insert(questionObj);
		
		return new ResponseEntity<QuestionDTO>(cf.mf.questionMapper.mapToDto(questionObj), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuestionDTO> update(QuestionDTO question) throws PermissionException, BusinessRuleException, DAOException {
		
		Question questionObj = cf.mf.questionMapper.mapToObj(question);
		cf.questionService.update(questionObj);
		
		return new ResponseEntity<QuestionDTO>(cf.mf.questionMapper.mapToDto(questionObj), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") int questionId) throws BusinessRuleException, PermissionException, DAOException {
		
		if (questionId <= 0)
			throw new BusinessRuleException("O id informado não é válido");
		
		Question question = cf.questionService.findById(questionId);
		cf.questionService.delete(question);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<QuestionDTO>> get() throws BusinessRuleException, PermissionException, DAOException {
		
		List<Question> questions = (List<Question>) cf.questionService.findAll();
		return new ResponseEntity<List<QuestionDTO>>(cf.mf.questionMapper.mapToDto(questions), HttpStatus.OK);
		
	}
}
