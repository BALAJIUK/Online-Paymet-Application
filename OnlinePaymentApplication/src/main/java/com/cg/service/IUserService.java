package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.entities.Customer;
@Service
public interface IUserService {
public Customer validateLogin(String mobileNumber,String password);
}
