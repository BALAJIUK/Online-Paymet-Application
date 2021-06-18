package com.cg.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.exception.CustomerNotFoundException;
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
		throw new CustomerNotFoundException("Mobile number or password incorrect.. Enter the correct credentials");
	}

	@Override
	public boolean register(Customer customer) {
		Customer cust = repo.getByMobileno(customer.getMobileNumber());
		if (cust == null) {
			repo.save(customer);
			return true;
		}
		throw new CustomerNotFoundException(customer.getMobileNumber()+" already registered..");
	}

	@Override
	public Customer getByWallet(Wallet wallet) {
		Customer cust = repo.getByWallet(wallet);
		return cust;
	}
}
