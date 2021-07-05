package com.cg;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockitoSession;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.entities.BenificiaryDetails;
import com.cg.entities.Customer;
import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
import com.cg.exception.BenificiaryNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.TransactionFailureException;
import com.cg.repositories.IBenificiaryRepository;
import com.cg.repositories.ITransactionRepository;
import com.cg.repositories.IUserRepository;
import com.cg.repositories.WalletRepository;
import com.cg.service.ITransactionService;
import com.cg.service.ITransactionServiceImplementation;
import com.cg.service.IUserService;
import com.cg.service.IuserServiceImplementation;
import com.cg.service.WalletService;
import com.cg.service.WalletServiceImplementation;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class WalletServiceTests {
	@MockBean
	private WalletRepository wrepo;
	@InjectMocks
	private WalletServiceImplementation service;
	@MockBean
	private IUserRepository urepo;
	@InjectMocks
	private IuserServiceImplementation uservice;
	@MockBean
	private IBenificiaryRepository brepo;
	@MockBean
	private ITransactionRepository trepo;

	@Test
	public void testCreateAccountFail() {
		String mobileNumber = "9876987698";
		Mockito.when(urepo.getByMobileno(mobileNumber)).thenReturn(null);
		assertThrows(CustomerNotFoundException.class, () -> service.createAccount(null, mobileNumber, null));
	}

	@Test
	public void testCreateAccountSuccess() {
		String mobileNumber = "9876987698";
		Customer cust = new Customer();
		cust.setMobileNumber(mobileNumber);
		cust.setWallet(null);
		Mockito.when(urepo.getByMobileno(mobileNumber)).thenReturn(cust);
		Customer cust1 = service.createAccount(null, mobileNumber, null);
		assertTrue(cust != null);
	}

	@Test
	public void testShowBalanceFail() {
		String mobile = "8765432109";
		Mockito.when(urepo.getByMobileno(mobile)).thenReturn(null);
		assertThrows(CustomerNotFoundException.class, () -> service.showBalance(mobile));
	}

	@Test
	public void testShowBalanceSuccesss() {
		String mobile = "8765432109";
		Customer cust = new Customer();
		cust.setMobileNumber(mobile);
		Mockito.when(urepo.getByMobileno(mobile)).thenReturn(cust);
		Customer cust1 = service.showBalance(mobile);
		assertTrue(cust1 != null);
	}

	// When the target customer is not registered
	@Test
	public void testFundTransferFail1() {
		Mockito.when(urepo.getByMobileno("8768768768")).thenReturn(null);
		assertThrows(CustomerNotFoundException.class, () -> service.fundTransfer("9999999999", "8768768768", null));
	}

	// When the target customer is not added to beneficiary details
	@Test
	public void testFundTransferFail2() {
		Wallet w = new Wallet();
		w.setWalletId(76);
		w.setBalance(new BigDecimal("0.0"));
		Wallet w1 = new Wallet();
		w1.setWalletId(77);
		w1.setBalance(new BigDecimal("0.0"));
		Customer cust = new Customer();
		cust.setMobileNumber("8888888888");
		cust.setWallet(w);
		Customer cust1 = new Customer();
		cust1.setWallet(w1);
		cust1.setMobileNumber("9999999999");
		Mockito.when(urepo.getByMobileno("9999999999")).thenReturn(cust1);
		Mockito.when(urepo.getByMobileno("8888888888")).thenReturn(cust);
		Mockito.when(brepo.getByMobAndWal("8888888888", w)).thenReturn(null);
		Mockito.when(wrepo.getByWalId(cust.getWallet().getWalletId())).thenReturn(w);
		Mockito.when(wrepo.getByWalId(cust1.getWallet().getWalletId())).thenReturn(w1);
		assertThrows(BenificiaryNotFoundException.class,
				() -> service.fundTransfer("9999999999", "8888888888", new BigDecimal("0.0")));

	}

	// If the Customer wallet balance becomes less than 0
	@Test
	public void testFundTransferFailure3() {
		Wallet w = new Wallet();
		w.setWalletId(76);
		w.setBalance(new BigDecimal("0.0"));
		Wallet w1 = new Wallet();
		w1.setWalletId(77);
		w1.setBalance(new BigDecimal("0.0"));
		Customer cust = new Customer();
		cust.setMobileNumber("8888888888");
		cust.setWallet(w);
		Customer cust1 = new Customer();
		cust1.setWallet(w1);
		cust1.setMobileNumber("9999999999");
		BenificiaryDetails bd = new BenificiaryDetails();
		bd.setMobileNumber("8888888888");
		bd.setWallet(w1);
		Mockito.when(urepo.getByMobileno("9999999999")).thenReturn(cust1);
		Mockito.when(urepo.getByMobileno("8888888888")).thenReturn(cust);
		Mockito.when(brepo.getByMobAndWal("8888888888", w1)).thenReturn(bd);
		Mockito.when(wrepo.getByWalId(cust.getWallet().getWalletId())).thenReturn(w);
		Mockito.when(wrepo.getByWalId(cust1.getWallet().getWalletId())).thenReturn(w1);
		assertThrows(TransactionFailureException.class,
				() -> service.fundTransfer("9999999999", "8888888888", new BigDecimal("50.0")));
	}

	@Test
	public void testUpdatePassFail() {
		Customer customer=new Customer();
		customer.setMobileNumber("8768768768");
		Mockito.when(urepo.getByMobileno("8768768768")).thenReturn(null);
		assertThrows(CustomerNotFoundException.class, ()->service.updateAccount(customer));
	}

	public void testUpdatePassSucces() {
		Customer customer=new Customer();
		customer.setMobileNumber("8768768768");
		Mockito.when(urepo.getByMobileno("8768768768")).thenReturn(customer);
		
		
	}
}
