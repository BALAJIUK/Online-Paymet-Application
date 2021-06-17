package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.BenificiaryDetails;
import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.repositories.IUserRepository;
import com.cg.service.IBenificiaryService;
import com.cg.service.IUserService;
import com.cg.service.WalletService;

@RestController
@RequestMapping("/api/v1")
public class BenificiaryController {

	@Autowired
	IBenificiaryService service;

	@Autowired
	WalletService wservice;

	@Autowired
	IUserService uservice;

	@PostMapping("/benificiary/add/{walletId}")
	public ResponseEntity<String> addBenificiaryDetails(@RequestBody BenificiaryDetails benificiaryDetails,
			@PathVariable("walletId") int walletId) {

		String msg = null;

		Wallet wallet = wservice.getById(walletId);

		benificiaryDetails.setWallet(wallet);

		BenificiaryDetails details = service.addBenificiary(benificiaryDetails);
		if (details == null) {
			msg = "Mobile number not registered or already added.. Cannot able to add";
		} else {
			msg = details.getMobileNumber() + " added successfully.!";
		}

		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/benificiary/view/{mobile}")
	public ResponseEntity<BenificiaryDetails> viewBenificiaryDetails(@PathVariable("mobile") String mobileNumber) {

		BenificiaryDetails details = service.viewBenificiary(mobileNumber);

		return new ResponseEntity<BenificiaryDetails>(details, HttpStatus.OK);
	}

	@DeleteMapping("/benificiary/delete/{walletId}/{mobile}")
	public ResponseEntity<List<BenificiaryDetails>> deleteBenificiaryDetails(@PathVariable("walletId") int walletId,
			@PathVariable("mobile") String mobileNumber) {

		Wallet wallet = wservice.getById(walletId);
		Customer customer = uservice.getByWallet(wallet);
		BenificiaryDetails bd = service.viewBenificiary(mobileNumber);
		service.deleteBenificiary(bd);

		List<BenificiaryDetails> benificiaryDetails = service.viewAllbBenificiary(customer);

		return new ResponseEntity<List<BenificiaryDetails>>(benificiaryDetails, HttpStatus.OK);
	}

	@GetMapping("/benificiary/view/all/{walletId}")
	public ResponseEntity<List<BenificiaryDetails>> getAllBenificiary(@PathVariable("walletId") int walletId) {

		Wallet wallet = wservice.getById(walletId);
		Customer cust = uservice.getByWallet(wallet);
		List<BenificiaryDetails> benificiaryDetails = service.viewAllbBenificiary(cust);

		return new ResponseEntity<List<BenificiaryDetails>>(benificiaryDetails, HttpStatus.OK);
	}
}
