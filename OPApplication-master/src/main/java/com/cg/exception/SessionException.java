package com.cg.exception;

public class SessionException extends RuntimeException{

private String message;

public SessionException(String message) {
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
