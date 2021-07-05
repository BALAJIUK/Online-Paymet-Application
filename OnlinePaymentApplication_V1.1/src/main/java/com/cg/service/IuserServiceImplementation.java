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
	IUserRepository userRepository;

	@Override
	public Customer validateLogin(String mobileNumber, String password) {
		Customer customer = userRepository.getByMobileno(mobileNumber);
		if (customer.getPassword().equals(password)) {
			return customer;
		}
		throw new CustomerNotFoundException("Mobile number or password incorrect.. Enter the correct credentials");
	}

	@Override
	public boolean register(Customer customer) {
		Customer customerFromDb = userRepository.getByMobileno(customer.getMobileNumber());
		if (customerFromDb == null) {
			userRepository.save(customer);
			return true;
		}
		throw new CustomerNotFoundException(customer.getMobileNumber()+" already registered..");
	}

	@Override
	public Customer getByWallet(Wallet wallet) {
		Customer customerFromDb = userRepository.getByWallet(wallet);
		return customerFromDb;
	}
}
