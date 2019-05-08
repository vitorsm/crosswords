package br.com.intcode.crosswords.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.intcode.crosswords.dto.UserDTO;
import br.com.intcode.crosswords.exceptions.BusinessRuleException;
import br.com.intcode.crosswords.exceptions.DAOException;
import br.com.intcode.crosswords.exceptions.PermissionException;
import br.com.intcode.crosswords.facade.CrosswordsFacade;
import br.com.intcode.crosswords.models.User;

@Service
public class UserMapper extends Mapper<User, UserDTO>{
	
	@Autowired
	private MapperFacade mf;
	
	@Autowired
	private CrosswordsFacade cf;
	
	@Override
	public User mapToObj(UserDTO dto) throws PermissionException, BusinessRuleException, DAOException {
		
		User user = null;
		
		if (dto.getId() > 0)
			user = cf.userService.findById(dto.getId());
		
		if (user == null) {
			user = new User();
			user.setId(dto.getId());
		}
		
		user.setLogin(dto.getLogin());
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		
		return user;
	}

	@Override
	public UserDTO mapToDto(User obj) throws PermissionException, BusinessRuleException, DAOException {
		
		UserDTO dto = mf.modelMapper.map(obj, UserDTO.class);
		return dto;
		
	}

}
