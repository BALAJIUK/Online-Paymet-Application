package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.entities.Transaction;
import com.cg.entities.Wallet;

@Service
public interface ITransactionService {
	
	public Transaction addTransaction(Transaction tran);

	public List<Transaction> viewAllTransactions(Wallet wallet);

	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to, Wallet wallet);

	public List<Transaction> viewAllTransactions(String transactionType,Wallet wallet);
}
