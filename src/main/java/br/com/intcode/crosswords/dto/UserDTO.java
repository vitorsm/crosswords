package br.com.intcode.crosswords.dto;

import lombok.Data;

@Data
public class UserDTO {

	private int id;
	private String login;
	private String name;
	private String password;
}
