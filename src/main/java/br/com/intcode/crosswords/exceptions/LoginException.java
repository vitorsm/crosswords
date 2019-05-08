package br.com.intcode.crosswords.exceptions;

public class LoginException extends Exception {
	
	public LoginException() {
		super();
	}
	
	public LoginException(String msg) {
		super(msg);
	}
	
	public LoginException(Throwable t) {
		super(t);
	}
	
	public LoginException(String msg, Throwable t) {
		super(msg, t);
	}
	
}