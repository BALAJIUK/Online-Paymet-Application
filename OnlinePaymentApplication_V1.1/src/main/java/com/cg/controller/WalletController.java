package com.cg.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.service.IUserService;
import com.cg.service.WalletService;

@RestController
@RequestMapping("api/v1")
public class WalletController {
	@Autowired
	WalletService walletService;

	@Autowired
	IUserService userService;

	@PostMapping(path = "/createwallet")
	public ResponseEntity<String> createWallet(@RequestBody Customer customer) {
		String message = null;
		Customer customerCheck = walletService.createAccount(customer.getName(), customer.getMobileNumber(), new BigDecimal("0.0"));
			message = "Wallet created Successfully for " + customerCheck.getMobileNumber();
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping(path = "/admin", produces = { "application/json" })
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> customers = walletService.getList();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@GetMapping(path = "/balance/{walletid}", produces = { "application/json" })
	public ResponseEntity<String> getBalance(@PathVariable("walletid") int walletid) {
		String msg = null;
		Wallet wallet = walletService.getById(walletid);
		String mobileno = walletService.getMobileByWallet(wallet);
		Customer customer = walletService.showBalance(mobileno);
		msg = "Your wallet balance : " + customer.getWallet().getBalance();
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PutMapping(path = "/updatepass", consumes = { "application/json" })
	public ResponseEntity<String> changePass(@RequestBody Customer customer) {
		String msg = null;
		Customer customerCheck = walletService.updateAccount(customer);
			msg = "Password updated Successfully for " + customerCheck.getMobileNumber();
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PostMapping(path = "/addmoney/{walletid}/{Accno}", consumes = { "application/json" })
	public ResponseEntity<String> addMoney(@PathVariable("walletid") int wid, @PathVariable("Accno") int accno,
			@RequestBody Map<String, String> requestbody) {
		String msg = null;
		double amount = Double.parseDouble(requestbody.get("amount"));
		Wallet wallet = walletService.getById(wid);
		wallet.getBankaccount().setAccountNo(accno);
		Customer customer = walletService.addMoney(wallet, amount);
			msg = amount + " added successfully to your wallet.";
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PostMapping(path = "/moneytransfer/{walletid}", consumes = { "application/json" })
	public ResponseEntity<String> fundTransfer(@PathVariable("walletid") int id,
			@RequestBody Map<String, String> requestbody) {
		String msg = null;
		Wallet wallet = walletService.getById(id);
		Customer customerCheck = userService.getByWallet(wallet);
		String source = customerCheck.getMobileNumber();
		String target = requestbody.get("target");
		double amount = Double.parseDouble(requestbody.get("amount"));
		BigDecimal bigDecimalAmount = new BigDecimal(amount);
		Customer customer = walletService.fundTransfer(source, target, bigDecimalAmount);
			msg = amount + " sent to " + customer.getName() + " successfully";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PostMapping(path = "/deposit/{walletid}", consumes = { "application/json" })
	public ResponseEntity<String> depositAmmount(@PathVariable("walletid") int id,
			@RequestBody Map<String, String> requestbody) {
		String msg = null;
		Wallet wallet = walletService.getById(id);
		int accno = Integer.parseInt(requestbody.get("account number"));
		double amount = Double.parseDouble(requestbody.get("amount"));
		Customer cust = walletService.depositAmount(wallet, accno, new BigDecimal(amount));
			msg = amount + " transfered from wallet to bank account " + accno;
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
