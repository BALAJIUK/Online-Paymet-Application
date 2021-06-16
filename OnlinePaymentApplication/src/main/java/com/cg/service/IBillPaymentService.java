package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.entities.BillPayment;

@Service
public interface IBillPaymentService {
	public BillPayment addBillPayment(BillPayment payment);

	public List<BillPayment> viewBillPayment(BillPayment payment);

}
