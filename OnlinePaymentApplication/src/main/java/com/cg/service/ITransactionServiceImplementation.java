package com.cg.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
import com.cg.repositories.ITransactionRepository;
@Service
public class ITransactionServiceImplementation implements ITransactionService {

	@Autowired
	ITransactionRepository repo;
	
	@Override
	public Transaction addTransaction(Transaction tran) {
		Transaction transaction = repo.save(tran);
		return transaction;
	}

	@Override
	public List<Transaction> viewAllTransactions(Wallet wallet) {
		List<Transaction> transactions = repo.findTransactionById(wallet);
		return transactions;
	}

	@Override
	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to, Wallet wallet) {

		List<Transaction> transactions = repo.findTransactionByDate(from, to, wallet);
		return transactions;
	}

	@Override
	public List<Transaction> viewAllTransactions(String type, Wallet wallet) {
		List<Transaction> transactions = repo.findTransactionById(wallet);
		List<Transaction> transactionswithtype = new ArrayList<Transaction>();
		for (Transaction t : transactions) {
			if (t.getTransactionType().contains(type)) {
				transactionswithtype.add(t);
			}
		}
		return transactionswithtype;
	}
}
