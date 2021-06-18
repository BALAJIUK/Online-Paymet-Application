package com.cg.exception;

public class TransactionFailureException extends RuntimeException {

	private String message;

	public TransactionFailureException(String message) {
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
