package com.cg.controller;

import java.time.LocalDate;
import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.BillPayment;
import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.security.utility.JWTUtility;
import com.cg.service.IBillPaymentService;
import com.cg.service.IUserService;
import com.cg.service.WalletService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class BillPaymentController {

	@Autowired
	IBillPaymentService billPaymentService;

	@Autowired
	WalletService walletService;
	
	@Autowired
	JWTUtility jwtUtility;
	
	@Autowired
	IUserService userService;

	//To make a bill payment
	@PostMapping("/billpayment/add")
	public ResponseEntity<String> addBill(@RequestHeader(name = "Authorization") String token,
			@Valid 	@RequestBody BillPayment billPayment) {

		String msg = null;
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		billPayment.setWallet(wallet);
		billPayment.setPaymentDate(LocalDate.now());
		BillPayment bill = billPaymentService.addBillPayment(billPayment);
		msg = "Bill Paid successfully ..! ₹" + bill.getAmount();
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}
	
	//To view all the bill payments made
	@GetMapping("/billpayments/view")
	public ResponseEntity<List<BillPayment>> viewBill(@RequestHeader(name = "Authorization") String token){
		
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		
		List<BillPayment> bills = billPaymentService.viewAllBills(wallet);
		Collections.reverse(bills);
		return new ResponseEntity<List<BillPayment>>(bills,HttpStatus.OK);
		
	}
}
