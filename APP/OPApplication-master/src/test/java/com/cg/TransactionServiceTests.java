package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
import com.cg.repositories.ITransactionRepository;
import com.cg.service.ITransactionServiceImplementation;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class TransactionServiceTests {

	@MockBean
	private ITransactionRepository repo;

	@InjectMocks
	private ITransactionServiceImplementation service;

	//Transaction added successfully
	@Test
	public void testAddTransactionSuccess() {
		Transaction t = new Transaction("type", LocalDate.now(), 90.0, "descript", new Wallet());
		Mockito.when(repo.save(t)).thenReturn(t);
		Transaction t1 = service.addTransaction(t);
		assertNotNull(t1);
	}

	//View transactions according to wallet 
	@Test
	public void testViewallTransactionsSuccess() {
		Wallet w = new Wallet();
		w.setWalletId(76);
		Transaction t = new Transaction("type", LocalDate.now(), 90.0, "descript", w);
		Transaction t1 = new Transaction("type", LocalDate.now(), 90.0, "descript", w);
		List<Transaction> transactions = new ArrayList<Transaction>();
		Mockito.when(repo.findTransactionById(w)).thenReturn(transactions);
		assertNotNull(service.viewAllTransactions(w));
	}

	//View transactions between two dates
	@Test
	public void testViewTransactionByDateSuccess() {
		Wallet w = new Wallet();
		w.setWalletId(76);
		Transaction t = new Transaction("bill", LocalDate.of(2021, 5, 21), 90.0, "descript", w);
		Transaction t1 = new Transaction("transfer", LocalDate.of(2021, 6, 19), 90.0, "descript", w);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(t1);
		Mockito.when(repo.findTransactionByDate(LocalDate.of(2021, 6, 1), LocalDate.of(2021, 6, 19), w))
				.thenReturn(transactions);
		assertEquals(transactions,
				service.viewTransactionByDate(LocalDate.of(2021, 6, 1), LocalDate.of(2021, 6, 19), w));
	}
	
	//view transaction by transaction type
	@Test
	public void testViewAllTransactionsByTypeSuccess() {
		Wallet w = new Wallet();
		w.setWalletId(76);
		Transaction t = new Transaction("bill", LocalDate.of(2021, 5, 21), 90.0, "descript", w);
		Transaction t1 = new Transaction("transfer", LocalDate.of(2021, 6, 19), 90.0, "descript", w);
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(t1);
		transactions.add(t);
		List<Transaction> transactionresult = new ArrayList<Transaction>();
		transactionresult.add(t1);
		Mockito.when(repo.findTransactionById(w)).thenReturn(transactions);
		assertEquals(transactionresult, service.viewAllTransactions("Transfer", w));
	}
}
