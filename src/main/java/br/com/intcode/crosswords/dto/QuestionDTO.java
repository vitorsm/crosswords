package br.com.intcode.crosswords.dto;

import java.util.Date;

import lombok.Data;

@Data
public class QuestionDTO {

	private int id;
	private String description;
	private String answer;
	private Date createdAt;
	private UserDTO createdBy;
	
}
