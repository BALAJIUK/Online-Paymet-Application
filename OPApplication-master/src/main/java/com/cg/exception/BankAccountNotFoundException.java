package com.cg.exception;

public class BankAccountNotFoundException extends RuntimeException {
	
	private String message;

	public BankAccountNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
