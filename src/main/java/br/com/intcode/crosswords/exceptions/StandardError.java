package br.com.intcode.crosswords.exceptions;

import lombok.Data;

@Data
public class StandardError {
	
	private Integer status;
	private String message;
	private Long timestamp;
	private Boolean showMessageToUser;
	private ErrorType errorType;
	
	public StandardError(Integer status, String message, Long timeStamp) {
		this.status = status;
		this.message = message;
		this.timestamp = timeStamp;
	}

}
