package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.entities.BenificiaryDetails;
import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.exception.BenificiaryNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.repositories.IBenificiaryRepository;
import com.cg.repositories.IUserRepository;
import com.cg.repositories.WalletRepository;
import com.cg.service.IBenificiaryServiceImplementation;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BenificiaryServiceTests {

	@InjectMocks
	private IBenificiaryServiceImplementation service;
	@MockBean
	private IBenificiaryRepository brepo;
	@MockBean
	private WalletRepository repo;
	@MockBean
	private IUserRepository urepo;

	// If the user tries to add a mobile number that is not registered
	@Test
	public void testAddBenificiaryFail() {
		String mobile = "8888888888";
		BenificiaryDetails bd = new BenificiaryDetails();
		bd.setMobileNumber(mobile);
		bd.setName("Ram");
		Mockito.when(urepo.getByMobileno(mobile)).thenReturn(null);
		assertThrows(CustomerNotFoundException.class, () -> service.addBenificiary(bd));
	}

	// If the user tries to add his mobile number
	@Test
	public void testAddBenificiaryFail2() {
		String mobile = "8888888888";
		Wallet w = new Wallet();
		w.setWalletId(89);
		w.setBalance(new BigDecimal("90.0"));
		BenificiaryDetails bd = new BenificiaryDetails();
		bd.setMobileNumber(mobile);
		bd.setName("Ram");
		bd.setWallet(w);
		Customer cust = new Customer();
		cust.setMobileNumber(mobile);
		cust.setWallet(w);
		Mockito.when(urepo.getByMobileno(mobile)).thenReturn(cust);
		Mockito.when(repo.getByWalId(w.getWalletId())).thenReturn(w);
		Mockito.when(urepo.getByWallet(w)).thenReturn(cust);
		assertThrows(BenificiaryNotFoundException.class, () -> service.addBenificiary(bd));
	}

	//If the user tries a mobile number for second time
	@Test
	public void testAddBenificiaryFail3() {
		String mobile = "8888888888";
		Wallet w = new Wallet();
		w.setWalletId(89);
		w.setBalance(new BigDecimal("90.0"));
		Wallet w1 = new Wallet();
		w1.setWalletId(90);
		w1.setBalance(new BigDecimal("90.0"));
		BenificiaryDetails bd = new BenificiaryDetails();
		bd.setMobileNumber(mobile);
		bd.setName("Ram");
		bd.setWallet(w);
		Customer cust = new Customer();
		cust.setMobileNumber(mobile);
		cust.setWallet(w);
		Customer cust1 = new Customer();
		Mockito.when(urepo.getByMobileno(mobile)).thenReturn(cust);
		Mockito.when(repo.getByWalId(w.getWalletId())).thenReturn(w);
		Mockito.when(urepo.getByWallet(w)).thenReturn(cust1);
		Mockito.when(brepo.getByMobAndWal(bd.getMobileNumber(), w)).thenReturn(bd);
		assertThrows(BenificiaryNotFoundException.class, () -> service.addBenificiary(bd));
	}

}
