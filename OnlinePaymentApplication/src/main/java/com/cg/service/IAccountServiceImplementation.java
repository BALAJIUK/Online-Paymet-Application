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
	IAccountRepository arepo;

	@Autowired
	WalletRepository wrepo;

	@Override
	public Wallet addAccount(BankAccount bankaccount) {
		BankAccount bacc = arepo.getByWalAndAcc(bankaccount.getAccountNo(), bankaccount.getWallet());
		if (bacc != null) {
			throw new BankAccountNotFoundException(
					"Bank Account " + bankaccount.getAccountNo() + " already added to your wallet..");
		}
		BankAccount acc = arepo.getByAccNo(bankaccount.getAccountNo());
		if (acc == null) {
			arepo.save(bankaccount);
			Wallet wallet = wrepo.getByWalId(bankaccount.getWallet().getWalletId());
			return wallet;
		} else {
			throw new BankAccountNotFoundException(
					"Bank Account " + bankaccount.getAccountNo() + " already registered with other wallet");
		}

	}

	@Override
	public Wallet removeAccount(BankAccount bankaccount) {
		BankAccount acc = arepo.getByWalAndAcc(bankaccount.getAccountNo(), bankaccount.getWallet());
		if (acc == null) {
			throw new BankAccountNotFoundException(
					"Can't delete. Bank Account " + bankaccount.getAccountNo() + " is not added to your wallet");
		} else {
			Wallet wallet = wrepo.getByWalId(bankaccount.getWallet().getWalletId());
			arepo.removeAccount(bankaccount.getWallet(), bankaccount.getAccountNo());
			return wallet;
		}
	}

	@Override
	public List<BankAccount> viewAllAccounts(Wallet wallet) {
		List<BankAccount> accounts = arepo.getByWallet(wallet);
		return accounts;

	}

	@Override
	public BankAccount getByAccNo(int accno) {
		BankAccount bacc = arepo.getByAccNo(accno);
		return bacc;
	}

}
