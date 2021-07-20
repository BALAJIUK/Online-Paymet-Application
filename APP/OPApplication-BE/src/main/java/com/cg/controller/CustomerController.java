package com.cg.controller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.CustomerToken;
import com.cg.repositories.CustomerTokenRepository;
import com.cg.security.models.JwtRequest;
import com.cg.security.models.JwtResponse;
import com.cg.security.utility.JWTUtility;
import com.cg.service.IuserServiceImplementation;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class CustomerController {
	@Autowired
	IuserServiceImplementation userService;
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomerTokenRepository customerTokenRepository;

    //To login
	@PostMapping(path = "/authenticate", consumes = { "application/json" })
	public ResponseEntity<String> loginCheck(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		 try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            jwtRequest.getMobileNumber(),
	                            jwtRequest.getPassword()
	                    )
	            );
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }

	        final UserDetails userDetails
	                = userService.loadUserByUsername(jwtRequest.getMobileNumber());

	        final String token =
	                jwtUtility.generateToken(userDetails);
	        
	        CustomerToken customerToken=new CustomerToken(jwtRequest.getMobileNumber(), token);
	        customerTokenRepository.save(customerToken);
	        JwtResponse jwtResponse=new JwtResponse(token);
		return new ResponseEntity<String>(jwtResponse.getToken(),HttpStatus.OK);
	}
	
	//To Logout
    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader (name="Authorization") String token) {
    	System.out.println(token);
    	String realToken=token.substring(7);
    	String mobile=jwtUtility.getMobileNoFromToken(realToken);
    	System.out.println(mobile);
    	customerTokenRepository.deleteById(mobile);
    	return new ResponseEntity<String>("Logged out Successfully",HttpStatus.OK);
    }

}
