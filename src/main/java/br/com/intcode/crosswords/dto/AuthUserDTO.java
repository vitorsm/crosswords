package br.com.intcode.crosswords.dto;

import lombok.Data;

@Data
public class AuthUserDTO {

	private int id;
	private String name;
	private String login;
	private String token;
	
}
