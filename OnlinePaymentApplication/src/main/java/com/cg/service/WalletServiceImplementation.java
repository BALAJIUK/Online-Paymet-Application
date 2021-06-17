package com.cg.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.BankAccount;
import com.cg.entities.BenificiaryDetails;
import com.cg.entities.Customer;
import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
import com.cg.repositories.IAccountRepository;
import com.cg.repositories.IBenificiaryRepository;
import com.cg.repositories.IUserRepository;
import com.cg.repositories.WalletRepository;

@Service
public class WalletServiceImplementation implements WalletService {
	@Autowired
	IUserRepository urepo;
	@Autowired
	WalletRepository wrepo;
	@Autowired
	IAccountRepository arepo;
	@Autowired
	IBenificiaryRepository brepo;
	@Autowired
	ITransactionService tservice;

	@Override
	public Customer createAccount(String name, String mobileno, BigDecimal amount) {
		Customer cust = urepo.getByMobileno(mobileno);
		if (cust == null) {
			return null;
		}
		if (cust.getWallet() == null) {
			Wallet w = new Wallet(amount);
			wrepo.save(w);
			urepo.addWallet(w, mobileno);
			return cust;
		}
		return null;
	}

	@Override
	public Customer showBalance(String mobileno) {
		Customer c = urepo.getByMobileno(mobileno);
		if (c == null) {
			return null;
		} else {
			return c;
		}
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		Customer custS = urepo.getByMobileno(sourceMobileNo);
		Customer custT = urepo.getByMobileno(targetMobileNo);
		if (custS == null || custT == null) {
			return null;
		}
		Wallet walletS = wrepo.getByWalId(custS.getWallet().getWalletId());
		Wallet walletT = wrepo.getByWalId(custT.getWallet().getWalletId());
		BenificiaryDetails detail = brepo.getByMobAndWal(targetMobileNo, walletS);
		double sourceAmount = walletS.getBalance().doubleValue();
		double targetAmount = walletT.getBalance().doubleValue();
		sourceAmount = sourceAmount - amount.doubleValue();
		targetAmount = targetAmount + amount.doubleValue();
		BigDecimal bdSource = new BigDecimal(sourceAmount);
		BigDecimal bdTarget = new BigDecimal(targetAmount);
		if (detail != null) {
			if (sourceAmount >= 0) {
				wrepo.updateBal(bdSource, walletS.getWalletId());
				wrepo.updateBal(bdTarget, walletT.getWalletId());
				Transaction t1 = new Transaction("Transfer", LocalDate.now(), amount.doubleValue(),
						"Sent to " + targetMobileNo, walletS);
				Transaction t2 = new Transaction("Transfer", LocalDate.now(), amount.doubleValue(),
						"Received from " + sourceMobileNo, walletT);
				tservice.addTransaction(t1);
				tservice.addTransaction(t2);
				return custT;
			}
		}
		return null;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getList() {
		List<Customer> customers = urepo.findAll();
		return customers;
	}

	@Override
	public Customer updateAccount(Customer customer) {
		Customer c = urepo.getByMobileno(customer.getMobileNumber());
		if (c == null) {
			return null;
		} else {
			urepo.updatePassword(customer.getPassword(), customer.getMobileNumber());
			return c;
		}
	}

	@Override
	public Customer addMoney(Wallet wallet, double amount) {
		int accno = wallet.getBankaccount().getAccountNo();
		BankAccount bacc = arepo.getByAccNo(accno);
		double wamount = wallet.getBalance().doubleValue();
		double bamount = bacc.getBalance();
		wamount = wamount + amount;
		bamount = bamount - amount;
		BigDecimal bdwamount = new BigDecimal(wamount);
		if (bacc.getWallet().getWalletId() == wallet.getWalletId()) {
			if (bamount >= 0) {
				bacc.setBalance(bamount);
				wallet.setBalance(bdwamount);
				arepo.updateBal(bamount, accno);
				wrepo.updateBal(bdwamount, wallet.getWalletId());
				Customer cust = urepo.getByWallet(wallet);
				return cust;
			}
		}
		return null;
	}

	@Override
	public Wallet getById(int id) {
		Wallet w = wrepo.getByWalId(id);
		if (w == null) {
			return null;
		} else {
			return w;
		}
	}

	@Override
	public String getMobileByWallet(Wallet wallet) {
		String mobile = urepo.getCustomer(wallet);
		return mobile;
	}

}
