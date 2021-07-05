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

import com.cg.entities.BankAccount;
import com.cg.entities.Wallet;
import com.cg.service.IAccountService;
import com.cg.service.WalletService;

@RestController
@RequestMapping("/api/v1")
public class BankAccountController {
	@Autowired
	WalletService walletService;
	@Autowired
	IAccountService accountService;
	@GetMapping(path = "/bankaccounts/{walletid}")
	public ResponseEntity<List<BankAccount>> getAllAccounts(@PathVariable("walletid") int id)
	{
		Wallet wallet=walletService.getById(id);
		List<BankAccount> bankaccounts=accountService.viewAllAccounts(wallet);
		return new ResponseEntity<List<BankAccount>>(bankaccounts,HttpStatus.OK);
	}

	@PostMapping(path = "/add/bankaccount/{walletId}", consumes = { "application/json" })
	public ResponseEntity<List<BankAccount>> addBankAccount(@RequestBody BankAccount bankAccount,
			@PathVariable int walletId) {

		Wallet wallet = walletService.getById(walletId);
		bankAccount.setWallet(wallet);
		accountService.addAccount(bankAccount);
		List<BankAccount> bankAccountList = accountService.viewAllAccounts(wallet);
		return new ResponseEntity<List<BankAccount>>(bankAccountList, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/bankaccount/delete/{walletId}/{accountNo}")
	public ResponseEntity<List<BankAccount>> removeAccount(@PathVariable("walletId") int walletId,
			@PathVariable("accountNo") int bankAccountNo) {

		Wallet wallet = walletService.getById(walletId);
		BankAccount bankAccount=accountService.getByAccNo(bankAccountNo);
		accountService.removeAccount(bankAccount);
		List<BankAccount> bankAccounts = accountService.viewAllAccounts(wallet);
		return new ResponseEntity<List<BankAccount>>(bankAccounts, HttpStatus.OK);

	}
	}

