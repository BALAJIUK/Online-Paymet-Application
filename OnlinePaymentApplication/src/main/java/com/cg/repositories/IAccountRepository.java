package com.cg.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entities.BankAccount;
import com.cg.entities.Wallet;

@Repository
public interface IAccountRepository extends JpaRepository<BankAccount, Integer>{
@Query("from BankAccount b where b.wallet=?1")
public List<BankAccount> getByWallet(Wallet wallet);
@Query("from BankAccount b where b.accountNo=?1")
public BankAccount getByAccNo(int accNo);
@Transactional
@Modifying
@Query("update BankAccount b set b.balance=?1 where b.accountNo=?2")
public void updateBal(double amount,int accno);
@Transactional
@Modifying
@Query("delete from BankAccount ba where ba.wallet=?1 and ba.accountNo=?2")
public void removeAccount(Wallet wallet, int accountNo);
@Query("from BankAccount b where b.accountNo=?1 and b.wallet=?2")
public BankAccount getByWalAndAcc(int acc,Wallet wallet);
}
