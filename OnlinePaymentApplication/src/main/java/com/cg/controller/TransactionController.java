package com.cg.controller;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    ITransactionService transactionservice;
	
	@Autowired
	WalletService walletservice;
	
	@PostMapping("/transaction/{walletId}")
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction,@PathVariable int walletId ){
		
	    Wallet wallet=walletservice.getById(walletId);
	    transaction.setWallet(wallet);
		
		Transaction transactions=transactionservice.addTransaction(transaction);
		
		return new ResponseEntity<Transaction>(transactions,HttpStatus.OK);
		
		
	}
	
	
	@GetMapping("/transactions/id/{walletId}")
	public ResponseEntity<List<Transaction>> viewAllTransactions(
			@PathVariable("walletId")Wallet wallet){
		
		
		List<Transaction> transactions=transactionservice.viewAllTransactions(wallet);
		
     	return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/transactions/{walletId}/{transactionDate1}/{transactionDate2}")
	public ResponseEntity<List<Transaction>> viewTransactionByDate(
			@PathVariable("walletId")int walletId,
			@PathVariable("transactionDate1") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate from,
			@PathVariable("transactionDate2")@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate to){
		
		 Wallet wallet=walletservice.getById(walletId);
		List<Transaction> transactions=transactionservice.viewTransactionByDate(from,to,wallet);
				return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/transaction/type/{walletId}/{transactionType}")
	public ResponseEntity<List<Transaction>> viewAllTransactions(
			@PathVariable("walletId")int walletId,
			@PathVariable("transactionType")String transactionType){
		
		 Wallet wallet=walletservice.getById(walletId);
		
		List<Transaction> transactions=transactionservice.viewAllTransactions(transactionType,wallet);
		
     	return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
		
	}
	
	
	

}
