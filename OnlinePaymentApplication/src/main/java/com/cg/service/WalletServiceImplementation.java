package com.cg.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.repositories.IUserRepository;
import com.cg.repositories.WalletRepository;

@Service
public class WalletServiceImplementation implements WalletService {
	@Autowired
	IUserRepository urepo;
	@Autowired
	WalletRepository wrepo;

	@Override
	public Customer createAccount(String name, String mobileno, BigDecimal amount) {
		List<Customer> customers = urepo.findAll();
		for (Customer cust : customers) {
			if (cust.getMobileNumber().equals(mobileno) && cust.getName().equals(name)) {
				if (cust.getWallet() == null) {
					Wallet w = new Wallet(amount);
					wrepo.save(w);
					urepo.addWallet(w, mobileno);
					return cust;
				}
			}
		}
		return null;
	}

	@Override
	public Customer showBalance(String mobileno) {
		List<Customer> customers=urepo.findAll();
		for(Customer c:customers)
		{
			if(c.getMobileNumber().equals(mobileno))
			{
				return c;
			}
		}
		return null;
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getList() {
		List<Customer> customers=urepo.findAll();
		return customers;
	}

	@Override
	public Customer updateAccount(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer addMoney(Wallet wallet, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public Wallet getById(int WalletId) {
		// TODO Auto-generated method stub
		Wallet wallet=wrepo.getById(WalletId);
		return wallet;
	}

}
