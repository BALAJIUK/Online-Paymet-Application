package com.cg.service;

import java.util.List;



import org.springframework.stereotype.Service;

import com.cg.entities.Customer;
import com.cg.entities.Wallet;



@Service
public interface IUserService {
	public Customer validateLogin(String mobileNumber, String password);

	public boolean register(Customer customer);
	
	public Customer getByWallet(Wallet wallet);
}
