package com.cg.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.Customer;
import com.cg.repositories.IUserRepository;
@Service
public class IuserServiceImplementation implements IUserService{
	@Autowired
	IUserRepository repo;
	@Override
	public Customer validateLogin(String mobileNumber, String password) {
		List<Customer> customers=repo.findAll();
		for(Customer cust:customers)
		{
			if(cust.getMobileNumber().equals(mobileNumber)&&cust.getPassword().equals(password))
			{
				return cust;
			}
		}
		return null;
	}

}
