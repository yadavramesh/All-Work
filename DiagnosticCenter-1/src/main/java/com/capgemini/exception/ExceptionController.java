package com.capgemini.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ExceptionController {
	@ExceptionHandler(value=NullException.class)
	public ResponseEntity<Object> handleNullException(NullException exception)
	{
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
    @ExceptionHandler(value=InvalidException.class)
    public ResponseEntity<Object> handleInvalidException(InvalidException exception)
	{
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
    
    
    @ExceptionHandler(value=WrongValueException.class)
    public ResponseEntity<Object> handleWrongValueException(WrongValueException exception)
	{
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
    
    @ExceptionHandler(value=DiagnosticCenterNotAdded.class)
	public ResponseEntity<Object> handleDiagnosticCenter(DiagnosticCenterNotAdded exception)
	{
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
    
    @ExceptionHandler(value=ResourceNotFoundException.class)
	public ResponseEntity<Object> handleDiagnosticCenterUpdate(ResourceNotFoundException exception)
	{
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=LabTestNotFoundException.class)
	public ResponseEntity<Object> handleTestNotAdded(LabTestNotFoundException exception)
	{
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=TestNotRemoveException.class)
	public ResponseEntity<Object> handleTestNotRemove(TestNotRemoveException exception)
	{
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=UserNotAddedException.class)
	public ResponseEntity<Object> handleUserNotAdded(UserNotAddedException exception)
	{
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=UserNotRemovedException.class)
	public ResponseEntity<Object> handleUserNotRemoved(UserNotRemovedException exception)
	{
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
	}
}
