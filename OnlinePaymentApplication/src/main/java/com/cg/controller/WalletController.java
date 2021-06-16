package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Customer;
import com.cg.service.WalletService;

@RestController
@RequestMapping("api/v1")
public class WalletController {
	@Autowired
	WalletService service;

	@PostMapping(path = "/createwallet")
	public ResponseEntity<String> createWallet(@RequestBody Customer customer) {
		String message = null;
		Customer cust = service.createAccount(customer.getName(), customer.getMobileNumber(), null);
		if (cust == null) {
			message = "Wallet already created for this mobileNo";
		} else {
			message = "Wallet created Successfully for " + cust.getMobileNumber();
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@GetMapping(path = "/admin" , produces = {"application/json"})
	public ResponseEntity<List<Customer>> getCustomers()
	{
		List<Customer> customers=service.getList();
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
}
