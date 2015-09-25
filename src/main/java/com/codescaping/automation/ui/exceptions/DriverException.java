package com.codescaping.automation.ui.exceptions;

public class DriverException extends Exception {
	
	private static final long serialVersionUID = 1159518894670458957L;
	

	public DriverException() {
		super();
	}
	
	public DriverException(String msg) {
		super(msg);
	}
	
	public DriverException(String msg, Throwable exp) {
		super(msg, exp);
	}

}
