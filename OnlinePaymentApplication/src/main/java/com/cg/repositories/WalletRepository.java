package com.cg.repositories;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

	@Query("from Wallet w where w.walletId=?1")
	public Wallet getByWalId(int id);

	@Transactional
	@Modifying
	@Query("update Wallet w set w.balance=?1 where w.walletId=?2")
	public int updateBal(BigDecimal amount, int id);
}
