package br.com.intcode.crosswords.exceptions;

public class DAOException extends Exception {
	
	public DAOException() {
		super();
	}
	
	public DAOException(String msg) {
		super(msg);
	}
	
	public DAOException(Throwable t) {
		super(t);
	}
	
	public DAOException(String msg, Throwable t) {
		super(msg, t);
	}
	
}
