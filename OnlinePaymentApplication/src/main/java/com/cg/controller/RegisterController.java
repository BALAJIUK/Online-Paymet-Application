package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Customer;
import com.cg.service.IUserService;

@RestController
@RequestMapping("api/v1")
public class RegisterController {
	@Autowired
	IUserService service;

	@PostMapping(path = "/register", consumes = { "application/json" })
	public ResponseEntity<String> userRegistration(@RequestBody Customer customer) {
		String message = null;
		boolean flag = service.register(customer);
		if (flag) {
			message = "Registered Successfully";
		} else {
			message = "Mobile number already registered";
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
