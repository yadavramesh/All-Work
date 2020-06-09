package com.capgemini.exception;

@SuppressWarnings("serial")
public class DiagnosticCenterNotRemovedException extends RuntimeException {
	public DiagnosticCenterNotRemovedException(String msg)
	{
		super(msg);
	}

}
