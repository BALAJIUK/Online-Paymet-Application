package com.cg.exception;



import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BankAccountNotFoundException.class)
	public ResponseEntity handleAccountNotFoundException(BankAccountNotFoundException e,WebRequest request)
	{
		ErrorDetails errordetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity(errordetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TransactionFailureException.class)
	public ResponseEntity handleTransactionFailureException(TransactionFailureException e,WebRequest request)
	{
		ErrorDetails errordetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity(errordetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity handleCustomerNotFoundException(CustomerNotFoundException e,WebRequest request)
	{
		ErrorDetails errordetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity(errordetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BenificiaryNotFoundException.class)
	public ResponseEntity handleBenificiaryException(BenificiaryNotFoundException e,WebRequest request)
	{
		ErrorDetails errordetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity(errordetails,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(SessionException.class)
	public ResponseEntity handleSessionException(SessionException e,WebRequest request)
	{
		ErrorDetails errordetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity(errordetails,HttpStatus.UNAUTHORIZED);
	}
}
