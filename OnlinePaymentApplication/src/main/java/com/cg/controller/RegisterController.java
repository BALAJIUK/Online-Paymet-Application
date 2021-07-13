package com.cg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.service.IUserService;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class RegisterController {
	@Autowired
	IUserService userService;

	//To register
	@PostMapping(path = "/register", consumes = { "application/json" })
	public ResponseEntity<String> userRegistration(@Valid @RequestBody Customer customer) {
		String message = null;
		try {
			Long mobile = Long.parseLong(customer.getMobileNumber());
		} catch (Exception e) {
			throw new CustomerNotFoundException("Please enter the mobile number correctly");
		}
		boolean flag = userService.register(customer);
		if (flag) {
			message = "Registered Successfully";
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
