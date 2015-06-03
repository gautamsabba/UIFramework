package com.ascendlearning.automation.ui.exceptions;

public class DriverException extends Exception {
	
	private static final long serialVersionUID = 1159518894670458957L;
	
	private String message;

	public DriverException() {
		super();
	}
	
	public DriverException(String msg) {
		super(msg);
		message = msg;		
	}
	
	public DriverException(String msg, Throwable exp) {
		super(msg, exp);
		message = msg;		
	}

}
