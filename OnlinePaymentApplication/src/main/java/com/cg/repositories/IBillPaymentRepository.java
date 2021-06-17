package com.cg.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entities.BillPayment;
import com.cg.entities.Wallet;

@Repository
public interface IBillPaymentRepository extends JpaRepository<BillPayment, Integer> {

	
	@Query("From BillPayment bd where bd.wallet = ?1")
	public List<BillPayment> viewBillPayment(Wallet wallet);
}
