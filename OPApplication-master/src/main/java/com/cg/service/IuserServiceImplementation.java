package com.cg.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.SessionException;
import com.cg.repositories.IUserRepository;

@Service
public class IuserServiceImplementation implements IUserService, UserDetailsService {
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
		throw new CustomerNotFoundException(customer.getMobileNumber() + " already registered..");
	}

	@Override
	public Customer getByWallet(Wallet wallet) {
		Customer customerFromDb = userRepository.getByWallet(wallet);
		return customerFromDb;
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = userRepository.getByMobileno(username);
		if (customer != null) {
			return new User(customer.getMobileNumber(), customer.getPassword(), new ArrayList<>());
		} else {
			throw new CustomerNotFoundException("Mobile number or password incorrect.. Enter the correct credentials");
		}
	}

	@Override
	public Customer getById(String id) {
		Customer customer=userRepository.getByMobileno(id);
		return customer;
	}

	@Override
	public void sessionExpired() {
		throw new SessionException("Login again to get access to this page");
	}
}
