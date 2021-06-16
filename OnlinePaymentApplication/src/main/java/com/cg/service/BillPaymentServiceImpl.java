package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.BillPayment;
import com.cg.repositories.IBillPaymentRepository;

@Service
public class BillPaymentServiceImpl implements IBillPaymentService {
	
	@Autowired
	private IBillPaymentRepository billpaymentRepo;

	@Override
	public BillPayment addBillPayment(BillPayment payment) {
		// TODO Auto-generated method stub
		BillPayment billPayment=billpaymentRepo.saveAndFlush(payment);
		return billPayment;
	}

	

	@Override
	public List<BillPayment> viewBillPayment(BillPayment payment) {
		// TODO Auto-generated method stub
		return billpaymentRepo.findAll();
		 
	}

}
