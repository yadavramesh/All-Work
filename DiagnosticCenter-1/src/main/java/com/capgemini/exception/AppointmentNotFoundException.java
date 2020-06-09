package com.capgemini.exception;

public class AppointmentNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	String errorMessage;
	public AppointmentNotFoundException(String errorMessage)
	{
		this.errorMessage=errorMessage;
	}
	public String toString() {
		return errorMessage;
	}
}
