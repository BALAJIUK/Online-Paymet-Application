package com.cg.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Customer;
import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
import com.cg.security.utility.JWTUtility;
import com.cg.service.ITransactionService;
import com.cg.service.IUserService;
import com.cg.service.WalletService;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

	@Autowired
	ITransactionService transactionService;
	
	@Autowired
	WalletService walletService;
	
	@Autowired
	JWTUtility jwtUtility;
	
	@Autowired
	IUserService userService;
	
	//To view all transactions of wallet
	@GetMapping("/transactions/id")
	public ResponseEntity<List<Transaction>> viewAllTransactions(@RequestHeader(name = "Authorization") String token) {
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		List<Transaction> transactions=transactionService.viewAllTransactions(wallet);
     	return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
	}
	
	//To view transactions made between two dates
	@GetMapping("/transactions/{transactionDate1}/{transactionDate2}")
	public ResponseEntity<List<Transaction>> viewTransactionByDate(
			@RequestHeader(name = "Authorization") String token,
			@PathVariable("transactionDate1") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate from,
			@PathVariable("transactionDate2")@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate to){
		
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());		 
		List<Transaction> transactions=transactionService.viewTransactionByDate(from,to,wallet);
				return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
		
	}
	
	//To view all transactions by type
	@GetMapping("/transaction/type/{transactionType}")
	public ResponseEntity<List<Transaction>> viewAllTransactions(
			@RequestHeader(name = "Authorization") String token,
			@PathVariable("transactionType")String transactionType) {
		
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		
		List<Transaction> transactions=transactionService.viewAllTransactions(transactionType,wallet);
		
     	return new ResponseEntity<List<Transaction>>(transactions,HttpStatus.OK);
		
	}
}
