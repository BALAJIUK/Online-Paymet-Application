package com.cg.controller;
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
import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
import com.cg.service.IBillPaymentService;


@RestController
@RequestMapping("/api/v1")
public class BillPaymentController {
	
	@Autowired
	private IBillPaymentService billpaymentservice;
	
	
	@PostMapping("/billpayment")
	public ResponseEntity<BillPayment> addBillPayment(@RequestBody BillPayment payment ){
		
		BillPayment billPayment=billpaymentservice.addBillPayment(payment);
		
		return new ResponseEntity<BillPayment>(billPayment,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/viewbillpayment")
public ResponseEntity<List<BillPayment>> viewBillPayment(@RequestBody BillPayment payment ){
		
		List<BillPayment> billPayment=billpaymentservice.viewBillPayment(payment);
		
		return new ResponseEntity<List<BillPayment>>(billPayment,HttpStatus.OK);
		
		
	}
}
