package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.cg.entities.BankAccount;
import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.security.utility.JWTUtility;
import com.cg.service.IAccountService;
import com.cg.service.IUserService;
import com.cg.service.WalletService;

@RestController
@RequestMapping("/api/v1")
public class BankAccountController {
	@Autowired
	WalletService walletService;
	@Autowired
	IAccountService accountService;
	@Autowired
	IUserService userService;
	@Autowired
	JWTUtility jwtUtility;

	//To display the bank accounts linked to wallet
	@GetMapping(path = "/bankaccounts")
	public ResponseEntity<List<BankAccount>> getAllAccounts(@RequestHeader(name = "Authorization") String token) {
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		List<BankAccount> bankaccounts = accountService.viewAllAccounts(wallet);
		return new ResponseEntity<List<BankAccount>>(bankaccounts, HttpStatus.OK);
	}

	//To link an account to the wallet
	@PostMapping(path = "/add/bankaccount", consumes = { "application/json" })
	public ResponseEntity<List<BankAccount>> addBankAccount(@Valid @RequestBody BankAccount bankAccount,
			@RequestHeader(name = "Authorization") String token) {
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		bankAccount.setWallet(wallet);
		accountService.addAccount(bankAccount);
		List<BankAccount> bankAccountList = accountService.viewAllAccounts(wallet);
		return new ResponseEntity<List<BankAccount>>(bankAccountList, HttpStatus.OK);
	}

	//To delete the bank account linked to wallet
	@DeleteMapping(path = "/bankaccount/delete/{accountNo}")
	public ResponseEntity<List<BankAccount>> removeAccount(@RequestHeader(name = "Authorization") String token,
			@PathVariable("accountNo") int bankAccountNo) {
		String realToken = token.substring(7);
		String mobileNo = jwtUtility.getMobileNoFromToken(realToken);
		Customer customer = userService.getById(mobileNo);
		Wallet wallet = walletService.getById(customer.getWallet().getWalletId());
		BankAccount bankAccount = accountService.getByAccNo(bankAccountNo);
		accountService.removeAccount(bankAccount);
		List<BankAccount> bankAccounts = accountService.viewAllAccounts(wallet);
		return new ResponseEntity<List<BankAccount>>(bankAccounts, HttpStatus.OK);

	}
}
