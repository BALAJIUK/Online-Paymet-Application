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
	IBenificiaryService benificiaryService;

	@Autowired
	WalletService walletService;

	@Autowired
	IUserService userService;

	@PostMapping("/benificiary/add/{walletId}")
	public ResponseEntity<String> addBenificiaryDetails(@RequestBody BenificiaryDetails benificiaryDetails,
			@PathVariable("walletId") int walletId) {

		String msg = null;

		Wallet wallet = walletService.getById(walletId);

		benificiaryDetails.setWallet(wallet);

		BenificiaryDetails detail = benificiaryService.addBenificiary(benificiaryDetails);
		msg = detail.getMobileNumber() + " added successfully.!";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	@GetMapping("/benificiary/view/{mobile}")
	public ResponseEntity<BenificiaryDetails> viewBenificiaryDetails(@PathVariable("mobile") String mobileNumber) {

		BenificiaryDetails detail = benificiaryService.viewBenificiary(mobileNumber);

		return new ResponseEntity<BenificiaryDetails>(detail, HttpStatus.OK);
	}

	@DeleteMapping("/benificiary/delete/{walletId}/{mobile}")
	public ResponseEntity<List<BenificiaryDetails>> deleteBenificiaryDetails(@PathVariable("walletId") int walletId,
			@PathVariable("mobile") String mobileNumber) {

		Wallet wallet = walletService.getById(walletId);
		Customer customer = userService.getByWallet(wallet);
		BenificiaryDetails bd = benificiaryService.viewBenificiary(mobileNumber);
		benificiaryService.deleteBenificiary(bd);

		List<BenificiaryDetails> benificiaryDetails = benificiaryService.viewAllbBenificiary(customer);

		return new ResponseEntity<List<BenificiaryDetails>>(benificiaryDetails, HttpStatus.OK);
	}

	@GetMapping("/benificiary/view/all/{walletId}")
	public ResponseEntity<List<BenificiaryDetails>> getAllBenificiary(@PathVariable("walletId") int walletId) {

		Wallet wallet = walletService.getById(walletId);
		Customer customer = userService.getByWallet(wallet);
		List<BenificiaryDetails> benificiaryDetails = benificiaryService.viewAllbBenificiary(customer);

		return new ResponseEntity<List<BenificiaryDetails>>(benificiaryDetails, HttpStatus.OK);
	}
}
