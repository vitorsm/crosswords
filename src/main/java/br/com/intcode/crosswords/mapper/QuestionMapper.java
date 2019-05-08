package br.com.intcode.crosswords.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.intcode.crosswords.dto.QuestionDTO;
import br.com.intcode.crosswords.exceptions.BusinessRuleException;
import br.com.intcode.crosswords.exceptions.DAOException;
import br.com.intcode.crosswords.exceptions.PermissionException;
import br.com.intcode.crosswords.facade.CrosswordsFacade;
import br.com.intcode.crosswords.models.Question;

@Service
public class QuestionMapper extends Mapper<Question, QuestionDTO>{

	@Autowired
	private MapperFacade mf;
	
	@Autowired
	private CrosswordsFacade cf;

	@Override
	public Question mapToObj(QuestionDTO dto) throws PermissionException, BusinessRuleException, DAOException {
		
		Question question = null;
		
		if (dto.getId() > 0)
			question = cf.questionService.findById(dto.getId());
		
		if (question == null) {
			question = new Question();
			question.setId(dto.getId());
		}
			
		question.setAnswer(dto.getAnswer());
		question.setDescription(dto.getDescription());
		
		return question;
	}

	@Override
	public QuestionDTO mapToDto(Question obj) throws PermissionException, BusinessRuleException, DAOException {
		
		QuestionDTO dto = new QuestionDTO();
		
		dto.setAnswer(obj.getAnswer());
		dto.setCreatedAt(obj.getCreatedAt());
		dto.setCreatedBy(mf.userMapper.mapToDto(obj.getCreatedBy()));
		dto.setDescription(obj.getDescription());
		dto.setId(obj.getId());
		
		return dto;
	}
	
	
}
