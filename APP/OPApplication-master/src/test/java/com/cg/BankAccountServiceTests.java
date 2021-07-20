package com.cg;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockitoSession;

import javax.security.auth.login.AccountNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.entities.BankAccount;
import com.cg.entities.Wallet;
import com.cg.exception.BankAccountNotFoundException;
import com.cg.repositories.IAccountRepository;
import com.cg.repositories.WalletRepository;
import com.cg.service.IAccountServiceImplementation;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTests {

	@MockBean
	IAccountRepository repo;

	@InjectMocks
	IAccountServiceImplementation service;

	@MockBean
	WalletRepository wrepo;

	//If user tries to add the same account for second time
	@Test
	public void testAddAccountFail() {
		Wallet w = new Wallet();
		BankAccount bacc = new BankAccount();
		bacc.setAccountNo(9087);
		bacc.setBalance(8976.98);
		bacc.setBankName("CG");
		bacc.setIfscCode("ifsc567");
		bacc.setWallet(w);
		Mockito.when(repo.getByWalAndAcc(bacc.getAccountNo(), w)).thenReturn(bacc);
		assertThrows(BankAccountNotFoundException.class, () -> service.addAccount(bacc));

	}

	//If the user tries to add the account which is linked with some other wallet
	@Test
	public void testAddAccountFail2() {
		Wallet w = new Wallet();
		BankAccount bacc = new BankAccount();
		bacc.setAccountNo(9087);
		bacc.setBalance(8976.98);
		bacc.setBankName("CG");
		bacc.setIfscCode("ifsc567");
		bacc.setWallet(w);
		Mockito.when(repo.getByAccNo(bacc.getAccountNo())).thenReturn(bacc);
		assertThrows(BankAccountNotFoundException.class, () -> service.addAccount(bacc));
	}

	//Account added successfully
	@Test
	public void testAddSuccess() {
		Wallet w = new Wallet();
		BankAccount bacc = new BankAccount();
		bacc.setAccountNo(9087);
		bacc.setBalance(8976.98);
		bacc.setBankName("CG");
		bacc.setIfscCode("ifsc567");
		bacc.setWallet(w);
		Mockito.when(repo.getByWalAndAcc(bacc.getAccountNo(), w)).thenReturn(null);
		Mockito.when(repo.getByAccNo(bacc.getAccountNo())).thenReturn(null);
		Mockito.when(repo.save(bacc)).thenReturn(bacc);
		Mockito.when(wrepo.getByWalId(bacc.getWallet().getWalletId())).thenReturn(w);
		Wallet w1 = service.addAccount(bacc);
		assertNotNull(w1);
	}

	//If user tries to remove account that is not linked with the wallet
	@Test
	public void testRemoveAccountFail() {
		Wallet w = new Wallet();
		BankAccount bacc = new BankAccount();
		bacc.setAccountNo(9087);
		bacc.setBalance(8976.98);
		bacc.setBankName("CG");
		bacc.setIfscCode("ifsc567");
		bacc.setWallet(w);
		Mockito.when(repo.getByWalAndAcc(bacc.getAccountNo(), w)).thenReturn(null);
		assertThrows(BankAccountNotFoundException.class, () -> service.removeAccount(bacc));
	}

	//Successfully removing account from wallet
	@Test
	public void testRemoveAccountSuccess() {
		Wallet w = new Wallet();
		BankAccount bacc = new BankAccount();
		bacc.setAccountNo(9087);
		bacc.setBalance(8976.98);
		bacc.setBankName("CG");
		bacc.setIfscCode("ifsc567");
		bacc.setWallet(w);
		Mockito.when(repo.getByWalAndAcc(bacc.getAccountNo(), w)).thenReturn(bacc);
		Mockito.when(wrepo.getByWalId(bacc.getWallet().getWalletId())).thenReturn(w);
		Wallet w1=service.removeAccount(bacc);
		assertTrue(w1!=null);
	}
}
