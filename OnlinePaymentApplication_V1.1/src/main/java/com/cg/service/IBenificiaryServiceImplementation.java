package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.BenificiaryDetails;
import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.exception.BenificiaryNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.repositories.IBenificiaryRepository;
import com.cg.repositories.IUserRepository;
import com.cg.repositories.WalletRepository;

@Service
public class IBenificiaryServiceImplementation implements IBenificiaryService {

	@Autowired
	IBenificiaryRepository benificiaryRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	WalletRepository walletRepository;

	@Override
	public BenificiaryDetails addBenificiary(BenificiaryDetails bd) {
		Customer customer = userRepository.getByMobileno(bd.getMobileNumber());
		if (customer == null) {
			throw new CustomerNotFoundException(
					bd.getMobileNumber() + " not registered...Enter the mobile number correctly");
		}
		Wallet wallet = walletRepository.getByWalId(bd.getWallet().getWalletId());
		Customer customerCheck = userRepository.getByWallet(wallet);
		if (bd.getMobileNumber().equals(customerCheck.getMobileNumber())) {
			throw new BenificiaryNotFoundException("Cannot add your mobile number to your benificiary details");
		}
		BenificiaryDetails bdetailFromDb = benificiaryRepository.getByMobAndWal(bd.getMobileNumber(), wallet);
		if (bdetailFromDb != null) {
			throw new BenificiaryNotFoundException("Mobile number already added to your benificiary details");
		} else {
			BenificiaryDetails detail = benificiaryRepository.save(bd);
			return detail;
		}
	}

	@Override
	public BenificiaryDetails deleteBenificiary(BenificiaryDetails bd) {
		benificiaryRepository.deleteById(bd.getWallet(), bd.getMobileNumber());
		return null;
	}

	@Override
	public BenificiaryDetails viewBenificiary(String mobileNo) {

		BenificiaryDetails detail = benificiaryRepository.findBymobileNumber(mobileNo);

		return detail;
	}

	@Override
	public List<BenificiaryDetails> viewAllbBenificiary(Customer customer) {
		Wallet wallet = customer.getWallet();
		List<BenificiaryDetails> allDetails = benificiaryRepository.viewAllBenificiaryById(wallet);
		return allDetails;
	}

}
