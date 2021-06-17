package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.BankAccount;
import com.cg.entities.Wallet;
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
		arepo.save(bankaccount);
		Wallet wallet=wrepo.getByWalId(bankaccount.getWallet().getWalletId());
		return wallet;
	}

	@Override
	public Wallet removeAccount(BankAccount bankaccount) {
		Wallet wallet=wrepo.getByWalId(bankaccount.getWallet().getWalletId());
		arepo.removeAccount(bankaccount.getWallet() ,bankaccount.getAccountNo());
		return wallet;
	}

	@Override
	public List<BankAccount> viewAllAccounts(Wallet wallet) {
		List<BankAccount> accounts=arepo.getByWallet(wallet);
		return accounts;

	}

	@Override
	public BankAccount getByAccNo(int accno) {
		BankAccount bacc=arepo.getByAccNo(accno);
		return bacc;
	}

}
