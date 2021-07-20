package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.BankAccount;
import com.cg.entities.Wallet;
import com.cg.exception.BankAccountNotFoundException;
import com.cg.repositories.IAccountRepository;
import com.cg.repositories.WalletRepository;

@Service
public class IAccountServiceImplementation implements IAccountService {

	@Autowired
	IAccountRepository accountRepository;

	@Autowired
	WalletRepository walletRepository;

	@Override
	public Wallet addAccount(BankAccount bankaccount) {
		BankAccount bankAccountFromDb = accountRepository.getByWalAndAcc(bankaccount.getAccountNo(), bankaccount.getWallet());
		if (bankAccountFromDb != null) {
			throw new BankAccountNotFoundException(
					"Bank Account " + bankaccount.getAccountNo() + " already added to your wallet..");
		}
		BankAccount bankAccount = accountRepository.getByAccNo(bankaccount.getAccountNo());
		if (bankAccount == null) {
			accountRepository.save(bankaccount);
			Wallet wallet = walletRepository.getByWalId(bankaccount.getWallet().getWalletId());
			return wallet;
		} else {
			throw new BankAccountNotFoundException(
					"Bank Account " + bankaccount.getAccountNo() + " already registered with other wallet");
		}

	}

	@Override
	public Wallet removeAccount(BankAccount bankaccount) {
		BankAccount bankAccountFromDb = accountRepository.getByWalAndAcc(bankaccount.getAccountNo(), bankaccount.getWallet());
		if (bankAccountFromDb == null) {
			throw new BankAccountNotFoundException(
					"Can't delete. Bank Account " + bankaccount.getAccountNo() + " is not added to your wallet");
		} else {
			Wallet wallet = walletRepository.getByWalId(bankaccount.getWallet().getWalletId());
			accountRepository.removeAccount(bankaccount.getWallet(), bankaccount.getAccountNo());
			return wallet;
		}
	}

	@Override
	public List<BankAccount> viewAllAccounts(Wallet wallet) {
		List<BankAccount> accounts = accountRepository.getByWallet(wallet);
		return accounts;

	}

	@Override
	public BankAccount getByAccNo(int accountNo) {
		BankAccount bankAccount = accountRepository.getByAccNo(accountNo);
		return bankAccount;
	}

}
