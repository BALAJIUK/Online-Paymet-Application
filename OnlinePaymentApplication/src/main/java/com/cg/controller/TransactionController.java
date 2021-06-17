package com.cg.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
import com.cg.service.ITransactionService;
import com.cg.service.WalletService;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	@Autowired
	ITransactionService service;
	
	@Autowired
	WalletService wservice;
	
	@GetMapping("/transactions/id/{walletId}")
	public ResponseEntity<List<Transaction>> viewAllTransactions(
			@PathVariable("walletId")Wallet wallet) {	
		List<Transaction> transactions=service.viewAllTransactions(wallet);
     	return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
	}
	
	@GetMapping("/transactions/{walletId}/{transactionDate1}/{transactionDate2}")
	public ResponseEntity<List<Transaction>> viewTransactionByDate(
			@PathVariable("walletId")int walletId,
			@PathVariable("transactionDate1") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate from,
			@PathVariable("transactionDate2")@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate to){
		
		 Wallet wallet=wservice.getById(walletId);
		 
		List<Transaction> transactions=service.viewTransactionByDate(from,to,wallet);
				return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
		
	}
	
	@GetMapping("/transaction/type/{walletId}/{transactionType}")
	public ResponseEntity<List<Transaction>> viewAllTransactions(
			@PathVariable("walletId")int walletId,
			@PathVariable("transactionType")String transactionType) {
		
		 Wallet wallet=wservice.getById(walletId);
		
		List<Transaction> transactions=service.viewAllTransactions(transactionType,wallet);
		
     	return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
		
	}
}
