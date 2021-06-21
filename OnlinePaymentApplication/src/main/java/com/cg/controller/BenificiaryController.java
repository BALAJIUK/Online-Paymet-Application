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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.BenificiaryDetails;
import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.repositories.IUserRepository;
import com.cg.security.utility.JWTUtility;
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

	@Autowired
	JWTUtility jwtUtility;

	//To add a Mobile number to benificiary details
	@PostMapping("/benificiary/add")
	public ResponseEntity<String> addBenificiaryDetails(@RequestBody BenificiaryDetails benificiaryDetails,
			@RequestHeader(name = "Authorization") String token) {

		String msg = null;

		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		benificiaryDetails.setWallet(wallet);

		BenificiaryDetails detail = benificiaryService.addBenificiary(benificiaryDetails);
		msg = detail.getMobileNumber() + " added successfully.!";
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

	//To get the benificiary detail by mobile number 
	@GetMapping("/benificiary/view/{mobile}")
	public ResponseEntity<BenificiaryDetails> viewBenificiaryDetails(@PathVariable("mobile") String mobileNumber,
			@RequestHeader(name = "Authorization") String token) {
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		BenificiaryDetails detail = benificiaryService.viewBenificiary(mobileNumber,wallet);

		return new ResponseEntity<BenificiaryDetails>(detail, HttpStatus.OK);
	}

	//To delete benificiary by mobile number
	@DeleteMapping("/benificiary/delete/{mobile}")
	public ResponseEntity<List<BenificiaryDetails>> deleteBenificiaryDetails(@RequestHeader(name = "Authorization") String token,
			@PathVariable("mobile") String mobileNumber) {

		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		BenificiaryDetails bd = benificiaryService.viewBenificiary(mobileNumber,wallet);
		benificiaryService.deleteBenificiary(bd);

		List<BenificiaryDetails> benificiaryDetails = benificiaryService.viewAllbBenificiary(customer);

		return new ResponseEntity<List<BenificiaryDetails>>(benificiaryDetails, HttpStatus.OK);
	}

	//To get all benificiary details linked to wallet
	@GetMapping("/benificiary/view/all")
	public ResponseEntity<List<BenificiaryDetails>> getAllBenificiary(@RequestHeader(name = "Authorization") String token) {

		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		List<BenificiaryDetails> benificiaryDetails = benificiaryService.viewAllbBenificiary(customer);

		return new ResponseEntity<List<BenificiaryDetails>>(benificiaryDetails, HttpStatus.OK);
	}
}
