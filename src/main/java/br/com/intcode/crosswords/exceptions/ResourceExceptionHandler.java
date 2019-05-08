package br.com.intcode.crosswords.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(DAOException.class)
	public ResponseEntity<StandardError> daoException(DAOException exception, HttpServletRequest request) {
		return generateStandardErrorResponse(exception, HttpStatus.BAD_GATEWAY, true, ErrorType.ERROR);
	}
	
	@ExceptionHandler(PermissionException.class)
	public ResponseEntity<StandardError> permissionException(PermissionException exception, HttpServletRequest request) {
		return generateStandardErrorResponse(exception, HttpStatus.FORBIDDEN, true, ErrorType.ERROR);
	}

	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<StandardError> businessRuleException(BusinessRuleException exception, HttpServletRequest request) {
		return generateStandardErrorResponse(exception, HttpStatus.BAD_REQUEST, true, ErrorType.ERROR);
	}
	
	private ResponseEntity<StandardError> generateStandardErrorResponse(
			Exception exception,
			HttpStatus status,
			boolean showMessageToUser,
			ErrorType errorType) {
		StandardError error = new StandardError(status.value(),
				exception.getMessage(),
				System.currentTimeMillis());
		error.setShowMessageToUser(showMessageToUser);
		error.setErrorType(errorType);
		
		return new ResponseEntity<StandardError>(error, status);
	}
	
	private ResponseEntity<StandardError> generateStandardErrorResponse(Exception exception, HttpStatus status) { 
		StandardError error = new StandardError(status.value(),
				exception.getMessage(),
				System.currentTimeMillis());
		
		return new ResponseEntity<StandardError>(error, status);
	}
}
