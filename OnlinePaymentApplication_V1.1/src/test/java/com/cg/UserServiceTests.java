package com.cg;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.entities.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.repositories.IUserRepository;
import com.cg.repositories.WalletRepository;
import com.cg.service.IuserServiceImplementation;
import com.cg.service.WalletServiceImplementation;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
	@MockBean
	private IUserRepository urepo;
	@InjectMocks
	private IuserServiceImplementation uservice;
	
	@Test
	public void testRegistrationFail()
	{
		String mobile="9876543210";
		Customer cust1=new Customer();
		cust1.setMobileNumber(mobile);
		Mockito.when(urepo.getByMobileno(mobile)).thenReturn(cust1);
		Customer cust=new Customer();
		cust.setMobileNumber(mobile);
		cust.setPassword(mobile);
		assertThrows(CustomerNotFoundException.class, ()->uservice.register(cust));
	}
	
	@Test
	public void testRegistrationSuccess()
	{
		String mobile="9876543210";
		Mockito.when(urepo.getByMobileno(mobile)).thenReturn(null);
		Customer cust=new Customer();
		cust.setMobileNumber(mobile);
		cust.setPassword(mobile);
		assertTrue(uservice.register(cust));
	}
	
}
