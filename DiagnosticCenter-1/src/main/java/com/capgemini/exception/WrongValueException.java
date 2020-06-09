package com.capgemini.exception;

@SuppressWarnings("serial")
public class WrongValueException extends RuntimeException {
	public WrongValueException (String msg)
	{
		super(msg);
	}
}
