package com.cg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entities.Wallet;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>{
	
	@Query("from Wallet w where w.walletId=?1")
	public Wallet getById(int WalletId);

}
