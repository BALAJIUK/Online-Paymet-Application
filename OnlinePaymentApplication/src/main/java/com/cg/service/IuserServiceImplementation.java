package com.cg.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.repositories.IUserRepository;

@Service
public class IuserServiceImplementation implements IUserService {
	@Autowired
	IUserRepository repo;

	@Override
	public Customer validateLogin(String mobileNumber, String password) {
		Customer cust = repo.getByMobileno(mobileNumber);
		if (cust.getPassword().equals(password)) {
			return cust;
		}
		return null;
	}

	@Override
	public boolean register(Customer customer) {
		Customer cust = repo.getByMobileno(customer.getMobileNumber());
		if (cust == null) {
			repo.save(customer);
			return true;
		}
		return false;
	}

	@Override
	public Customer getByWallet(Wallet wallet) {
		Customer cust=repo.getByWallet(wallet);
		return cust;
	}
}
