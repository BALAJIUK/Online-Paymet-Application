package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Customer;
import com.cg.service.IUserService;

@RestController
@RequestMapping("api/v1")
public class CustomerController {
	@Autowired
	IUserService service;

	@PostMapping(path = "/login", consumes = { "application/json" })
	public ResponseEntity<String> loginCheck(@RequestBody Customer customer) {
		String message = null;
		Customer cust = service.validateLogin(customer.getMobileNumber(), customer.getPassword());
		if (cust == null) {
			message = "Username or Password Incorrect.. Please enter the correct Credentials...!";
		} else {
			message = "Welcome back " + cust.getName();
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
