package com.capgemini.exception;

public class UserNotRemovedException extends RuntimeException {
	public UserNotRemovedException(String msg)
	{
		super(msg);
	}
}
