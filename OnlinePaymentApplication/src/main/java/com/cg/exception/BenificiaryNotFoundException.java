package com.cg.exception;

public class BenificiaryNotFoundException extends RuntimeException{

	
	private String message;

	public BenificiaryNotFoundException(String message) {
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
