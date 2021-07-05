package com.cg.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entities.Transaction;
import com.cg.entities.Wallet;
@Repository
public interface ITransactionRepository extends JpaRepository<Transaction,Integer>{

	@Query("From Transaction t where t.wallet=?1")
	List<Transaction> findTransactionById(Wallet wallet);
    
	
    @Query("From Transaction t where t.transactionDate between ?1 and ?2 and t.wallet=?3")
	List<Transaction> findTransactionByDate(LocalDate from, LocalDate to, Wallet walletId);
	
	@Query("From Transaction t where t.transactionType=?1 and t.wallet=?2")
    List<Transaction> findTransactionByType(String transactionType,Wallet walletId);


}
