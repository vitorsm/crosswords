package br.com.intcode.crosswords.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class MapperFacade {

	public static final ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	public QuestionMapper questionMapper;
	
	@Autowired
	public UserMapper userMapper;
}
