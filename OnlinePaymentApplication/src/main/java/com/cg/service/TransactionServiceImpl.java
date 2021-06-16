package com.cg.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
import com.cg.repositories.ITransactionRepository;

@Service
public class TransactionServiceImpl implements ITransactionService {
	
	@Autowired
	private ITransactionRepository transactionRepo;

	@Override
	public Transaction addTransaction(Transaction tran) {
		// TODO Auto-generated method stub
		Transaction transaction=transactionRepo.saveAndFlush(tran);
		return transaction;
	}

	@Override
	public List<Transaction> viewAllTransactions(Wallet wallet) {
		// TODO Auto-generated method stub
		List<Transaction> transaction=transactionRepo.findTransactionById(wallet);
		
		return transaction;
	}

	@Override
	public List<Transaction> viewTransactionByDate(LocalDate from, LocalDate to,Wallet walletId) {
		// TODO Auto-generated method stub
		List<Transaction> transaction=transactionRepo.findTransactionByDate(from,to,walletId);
		
		return transaction;
	}

	@Override
	public List<Transaction> viewAllTransactions(String transactionType,Wallet walletId) {
		// TODO Auto-generated method stub
		List<Transaction> transaction=transactionRepo.findTransactionByType(transactionType,walletId);
		return transaction;
	}

	

	
	

}
