package com.cg.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.entities.Customer;
import com.cg.entities.Wallet;

@Service
public interface WalletService {
	public Customer createAccount(String name, String mobileno, BigDecimal amount);

	public Customer showBalance(String mobileno);

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount);

	public Customer depositAmount(Wallet wallet,int accno, BigDecimal amount);

	public List<Customer> getList();

	public Customer updateAccount(Customer customer);

	public Customer addMoney(Wallet wallet, double amount);

	public Wallet getById(int id);

	public String getMobileByWallet(Wallet wallet);
}
