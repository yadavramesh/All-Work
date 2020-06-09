package com.capgemini.exception;

@SuppressWarnings("serial")
public class InvalidException extends RuntimeException{
	public InvalidException (String msg)
	{
		super(msg);
	}
}
