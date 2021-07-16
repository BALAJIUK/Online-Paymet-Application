package com.cg.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.SessionException;
import com.cg.exception.TransactionFailureException;
import com.cg.security.utility.JWTUtility;
import com.cg.service.IUserService;
import com.cg.service.WalletService;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class WalletController {
	@Autowired
	WalletService walletService;

	@Autowired
	IUserService userService;

	@Autowired
	JWTUtility jwtUtility;

	//To create a wallet
	@GetMapping(path = "/createwallet")
	public ResponseEntity<Customer> createWallet(@RequestHeader(name = "Authorization") String token) {
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Customer customerCheck = walletService.createAccount(customer.getName(), customer.getMobileNumber(),
				new BigDecimal("0.0"));
		return new ResponseEntity<Customer>(customerCheck, HttpStatus.OK);
	}

	//To get the list users
	@GetMapping(path = "/admin", produces = { "application/json" })
	public ResponseEntity<List<Customer>> getCustomers(@RequestHeader(name = "Authorization") String token) {
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		List<Customer> customers;
		if (customer.getName().equalsIgnoreCase("admin")) {
			customers = walletService.getList();
		} else {
			throw new CustomerNotFoundException("You don't have access to this page");
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	//To get the Wallet balance
	@GetMapping(path = "/balance", produces = { "application/json" })
	public ResponseEntity<String> getBalance(@RequestHeader(name = "Authorization") String token) {
		String msg = null;
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		String mobileno = walletService.getMobileByWallet(wallet);
		Customer customerCheck = walletService.showBalance(mobileno);
		msg =  String.valueOf(customerCheck.getWallet().getBalance());
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	//To update the password
	@PutMapping(path = "/updatepass", consumes = { "application/json" })
	public ResponseEntity<String> changePass(@RequestBody Customer customer,
			@RequestHeader(name = "Authorization") String token) {
		String msg = null;
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customerFromDb = userService.getById(mobileNo);
		customerFromDb.setPassword(customer.getPassword());
		Customer customerCheck = walletService.updateAccount(customerFromDb);
		msg = "Password updated Successfully" ;
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	//To add money from bank to wallet
	@PostMapping(path = "/addmoney", consumes = { "application/json" })
	public ResponseEntity<String> addMoney(
			@RequestHeader(name = "Authorization") String token, @RequestBody Map<String, String> requestbody) {
		String msg = null;
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		double amount = Double.parseDouble(requestbody.get("amount"));
		int accno = Integer.parseInt(requestbody.get("accountNumber"));
		if(amount==0) {
			throw new TransactionFailureException("Enter an amount greater than 0");
		}
		wallet.getBankaccount().setAccountNo(accno);
		Customer customerCheck = walletService.addMoney(wallet, amount);
		msg = "₹ "+amount + " added successfully to your wallet.";
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	//To transfer money from wallet to wallet
	@PostMapping(path = "/moneytransfer", consumes = { "application/json" })
	public ResponseEntity<String> fundTransfer(@RequestHeader(name = "Authorization") String token,
			@RequestBody Map<String, String> requestbody) {
		String msg = null;
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		String source = customer.getMobileNumber();
		String target=requestbody.get("target");
		double amount = Double.parseDouble(requestbody.get("amount"));
		if(amount==0) {
			throw new TransactionFailureException("Enter an amount greater than 0");
		}
		BigDecimal bigDecimalAmount = new BigDecimal(amount);
		Customer customerCheck2 = walletService.fundTransfer(source, target, bigDecimalAmount);
		msg = "₹ "+amount + " sent to " + customerCheck2.getName() + " successfully";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	//Tp deposit money from wallet to bank which is linked to wallet
	@PostMapping(path = "/deposit", consumes = { "application/json" })
	public ResponseEntity<String> depositAmmount(@RequestHeader(name = "Authorization") String token,
			@RequestBody Map<String, String> requestbody) {
		String msg = null;
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		int accno = Integer.parseInt(requestbody.get("accountNumber"));
		double amount = Double.parseDouble(requestbody.get("amount"));
		if(amount==0) {
			throw new TransactionFailureException("Enter an amount greater than 0");
		}
		Customer cust = walletService.depositAmount(wallet, accno, new BigDecimal(amount));
		msg = "₹ "+amount + " transfered from wallet to bank account " + accno;
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping(path = "/customer", produces = { "application/json" })
	public ResponseEntity<Customer> getCustomer(@RequestHeader(name = "Authorization") String token) {
		String msg = null;
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
}
