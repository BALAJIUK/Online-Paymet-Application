package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.entities.BankAccount;
import com.cg.entities.BenificiaryDetails;
import com.cg.entities.Customer;
import com.cg.entities.Wallet;
import com.cg.exception.BankAccountNotFoundException;
import com.cg.exception.BenificiaryNotFoundException;
import com.cg.exception.CustomerNotFoundException;
import com.cg.exception.TransactionFailureException;
import com.cg.repositories.IAccountRepository;
import com.cg.repositories.IBenificiaryRepository;
import com.cg.repositories.ITransactionRepository;
import com.cg.repositories.IUserRepository;
import com.cg.repositories.WalletRepository;
import com.cg.service.IuserServiceImplementation;
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
	@MockBean
	IAccountRepository arepo;

	//if the wallet already created for the customer it won't create again
	@Test
	public void testCreateAccountFail() {
		String mobileNumber = "9876987698";
		Mockito.when(urepo.getByMobileno(mobileNumber)).thenReturn(null);
		assertThrows(CustomerNotFoundException.class, () -> service.createAccount(null, mobileNumber, null));
	}

	//Successful wallet creation
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
	
	
	//if the mobile number entered wrongly
	@Test
	public void testShowBalanceFail() {
		String mobile = "8765432109";
		Mockito.when(urepo.getByMobileno(mobile)).thenReturn(null);
		assertThrows(CustomerNotFoundException.class, () -> service.showBalance(mobile));
	}

	//Succesfully displaying balance
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

	//if mobile number entered wrongly
	@Test
	public void testUpdatePassFail() {
		Customer customer = new Customer();
		customer.setMobileNumber("8768768768");
		Mockito.when(urepo.getByMobileno("8768768768")).thenReturn(null);
		assertThrows(CustomerNotFoundException.class, () -> service.updateAccount(customer));
	}

	//Successfully updating password
	@Test
	public void testUpdatePassSucces() {
		Customer customer = new Customer();
		customer.setMobileNumber("8768768768");
		Mockito.when(urepo.getByMobileno("8768768768")).thenReturn(customer);
		Mockito.when(urepo.updatePassword("jhbhjbj", customer.getMobileNumber())).thenReturn(1);
		assertEquals(customer, service.updateAccount(customer));
	}

	//If customer enters a bank account that is not registered with application
	@Test
	public void testAddMoneyFail() {
		Wallet wallet = new Wallet();
		wallet.setWalletId(76);
		Mockito.when(arepo.getByAccNo(908900)).thenReturn(null);
		assertThrows(BankAccountNotFoundException.class, () -> service.addMoney(wallet, 90.0));
	}

	//If customer enters a bank account that is not linked to wallet
	@Test
	public void testAddMoneyFail2() {
		Wallet wallet = new Wallet();
		wallet.setWalletId(76);
		Wallet wallet1 = new Wallet();
		wallet.setWalletId(78);
		BankAccount bacc = new BankAccount();
		bacc.setBalance(190.00);
		bacc.setWallet(wallet1);
		Mockito.when(arepo.getByAccNo(908900)).thenReturn(bacc);
		assertThrows(BankAccountNotFoundException.class, () -> service.addMoney(wallet, 90.0));
	}

	//If customer entered a bank account that is not linked to wallet
	@Test
	public void testDepositFail() {
		Wallet wallet = new Wallet();
		wallet.setWalletId(76);
		int acc=987987;
		Mockito.when(arepo.getByWalAndAcc(acc, wallet)).thenReturn(null);
		assertThrows(TransactionFailureException.class,
				() -> service.depositAmount(wallet, acc, new BigDecimal("0.0")));
	}

	//If customer entered a amount greater than wallet amount 
	@Test
	public void testDepositFail2() {
		Wallet wallet = new Wallet();
		wallet.setWalletId(76);
		wallet.setBalance(new BigDecimal("90.00"));
		int accno=89798;
		BankAccount bacc = new BankAccount();
		bacc.setBalance(190.00);
		bacc.setWallet(wallet);
		Mockito.when(arepo.getByWalAndAcc(accno, wallet)).thenReturn(bacc);
		assertThrows(TransactionFailureException.class,()->service.depositAmount(wallet, accno, new BigDecimal("890.00")));
	}
}
