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
	WalletService service;

	@Autowired
	IUserService uservice;

	@PostMapping(path = "/createwallet")
	public ResponseEntity<String> createWallet(@RequestBody Customer customer) {
		String message = null;
		Customer cust = service.createAccount(customer.getName(), customer.getMobileNumber(), new BigDecimal("0.0"));
		if (cust == null) {
			message = "Wallet already created for this mobileNo";
		} else {
			message = "Wallet created Successfully for " + cust.getMobileNumber();
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping(path = "/admin", produces = { "application/json" })
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> customers = service.getList();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@GetMapping(path = "/balance/{walletid}", produces = { "application/json" })
	public ResponseEntity<String> getBalance(@PathVariable("walletid") int walletid) {
		String msg = null;
		Wallet w = service.getById(walletid);
		String mobileno = service.getMobileByWallet(w);
		Customer cust = service.showBalance(mobileno);
		msg = "Your wallet balance : " + cust.getWallet().getBalance();
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PutMapping(path = "/updatepass", consumes = { "application/json" })
	public ResponseEntity<String> changePass(@RequestBody Customer customer) {
		String msg = null;
		Customer cust = service.updateAccount(customer);
		if (cust == null) {
			msg = "Updation failed...!";
		} else {
			msg = "Password updated Successfully for " + cust.getMobileNumber();
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PostMapping(path = "/addmoney/{walletid}/{Accno}", consumes = { "application/json" })
	public ResponseEntity<String> addMoney(@PathVariable("walletid") int wid, @PathVariable("Accno") int accno,
			@RequestBody Map<String, String> requestbody) {
		String msg = null;
		double amount = Double.parseDouble(requestbody.get("amount"));
		Wallet wallet = service.getById(wid);
		wallet.getBankaccount().setAccountNo(accno);
		Customer customer = service.addMoney(wallet, amount);
		if (customer == null) {
			msg = "Transaction failed.....!";
		} else {
			msg = amount + " added successfully to your wallet.";
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PostMapping(path = "/moneytransfer/{walletid}", consumes = { "application/json" })
	public ResponseEntity<String> fundTransfer(@PathVariable("walletid") int id,
			@RequestBody Map<String, String> requestbody) {
		String msg = null;
		Wallet wallet = service.getById(id);
		Customer cust = uservice.getByWallet(wallet);
		String source = cust.getMobileNumber();
		String target = requestbody.get("target");
		double amount = Double.parseDouble(requestbody.get("amount"));
		BigDecimal bdAmount = new BigDecimal(amount);
		Customer customer = service.fundTransfer(source, target, bdAmount);
		if (customer == null) {
			msg = "Transaction failed..";
		} else {
			msg = amount + " sent to " + customer.getName() + " successfully";
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@PostMapping(path = "/deposit/{walletid}", consumes = { "application/json" })
	public ResponseEntity<String> depositAmmount(@PathVariable("walletid") int id,
			@RequestBody Map<String, String> requestbody) {
		String msg = null;
		Wallet wallet = service.getById(id);
		int accno = Integer.parseInt(requestbody.get("account number"));
		double amount = Double.parseDouble(requestbody.get("amount"));
		Customer cust = service.depositAmount(wallet, accno, new BigDecimal(amount));
		if (cust != null) {
			msg = amount + " transfered from wallet to bank account " + accno;
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}
