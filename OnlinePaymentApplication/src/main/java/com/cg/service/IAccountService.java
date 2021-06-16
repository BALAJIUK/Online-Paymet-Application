package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.entities.BankAccount;
import com.cg.entities.Wallet;
@Service
public interface IAccountService {
	public Wallet addAccount(BankAccount bankaccount);
	public Wallet removeAccount(BankAccount bankaccount);
	public BankAccount viewAccount(Wallet wallet);
	public List<BankAccount> viewAllAccounts(Wallet wallet);
}
