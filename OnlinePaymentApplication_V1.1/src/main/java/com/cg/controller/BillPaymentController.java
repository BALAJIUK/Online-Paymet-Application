package com.cg.controller;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.BillPayment;
import com.cg.entities.Wallet;
import com.cg.service.IBillPaymentService;
import com.cg.service.WalletService;

@RestController
@RequestMapping("/api/v1")
public class BillPaymentController {

	@Autowired
	IBillPaymentService billPaymentService;

	@Autowired
	WalletService walletService;

	@PostMapping("/billpayment/add/{walletId}")
	public ResponseEntity<String> addBill(@PathVariable("walletId") int walletId,
			@RequestBody BillPayment billPayment) {

		String msg = null;
		Wallet wallet = walletService.getById(walletId);
		billPayment.setWallet(wallet);
		billPayment.setPaymentDate(LocalDate.now());
		BillPayment bill = billPaymentService.addBillPayment(billPayment);
		msg = "Transaction successfull ..! " + bill.getBillType() + " " + bill.getAmount();
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}
	
	@GetMapping("/billpayments/view/{walletId}")
	public ResponseEntity<List<BillPayment>> viewBill(@PathVariable("walletId") int walletId){
		
		Wallet wallet = walletService.getById(walletId);
		
		List<BillPayment> bills = billPaymentService.viewAllBills(wallet);
	
		return new ResponseEntity<List<BillPayment>>(bills,HttpStatus.OK);
		
	}
}
