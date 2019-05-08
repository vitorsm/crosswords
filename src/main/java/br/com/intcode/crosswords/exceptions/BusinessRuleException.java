package br.com.intcode.crosswords.exceptions;

public class BusinessRuleException extends Exception {
	
	public BusinessRuleException() {
		super();
	}
	
	public BusinessRuleException(String msg) {
		super(msg);
	}
	
	public BusinessRuleException(Throwable t) {
		super(t);
	}
	
	public BusinessRuleException(String msg, Throwable t) {
		super(msg, t);
	}
	
}