package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.BenificiaryDetails;
import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.repositories.IBenificiaryRepository;
import com.cg.repositories.IUserRepository;
import com.cg.repositories.WalletRepository;

@Service
public class IBenificiaryServiceImplementation implements IBenificiaryService {

	@Autowired
	IBenificiaryRepository brepo;

	@Autowired
	IUserRepository urepo;

	@Autowired
	WalletRepository wrepo;

	@Override
	public BenificiaryDetails addBenificiary(BenificiaryDetails bd) {
		Customer cust = urepo.getByMobileno(bd.getMobileNumber());
		Wallet w = wrepo.getByWalId(bd.getWallet().getWalletId());
		List<BenificiaryDetails> bdetails = brepo.viewAllBenificiaryById(w);
		for (BenificiaryDetails b : bdetails) {
			if (b.getMobileNumber().equals(bd.getMobileNumber())) {
				return null;
			}
		}
		if (cust == null) {
			return null;
		} else {
			BenificiaryDetails details = brepo.save(bd);
			return details;
		}
	}

	@Override
	public BenificiaryDetails deleteBenificiary(BenificiaryDetails bd) {
		brepo.deleteById(bd.getWallet(), bd.getMobileNumber());
		return null;
	}

	@Override
	public BenificiaryDetails viewBenificiary(String mobileNo) {

		BenificiaryDetails details = brepo.findBymobileNumber(mobileNo);

		return details;
	}

	@Override
	public List<BenificiaryDetails> viewAllbBenificiary(Customer customer) {
		Wallet wallet = customer.getWallet();
		List<BenificiaryDetails> allDetails = brepo.viewAllBenificiaryById(wallet);
		return allDetails;
	}

}
