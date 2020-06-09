package com.capgemini.exception;

@SuppressWarnings("serial")
public class AppointmentNotRemovedException extends RuntimeException {
	public AppointmentNotRemovedException(String msg)
	{
		super(msg);
	}

}
