package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.entities.BenificiaryDetails;
import com.cg.entities.Customer;
import com.cg.entities.Wallet;

@Service
public interface IBenificiaryService {
	
	public BenificiaryDetails addBenificiary(BenificiaryDetails bd);

	public BenificiaryDetails deleteBenificiary(BenificiaryDetails bd);

	public BenificiaryDetails viewBenificiary(String mobileNo,Wallet wallet);

	public List<BenificiaryDetails> viewAllbBenificiary(Customer customer);
	
	public Customer viewByMobile(String mobile);
}
