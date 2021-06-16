package com.cg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.BillPayment;
@Repository
public interface IBillPaymentRepository extends JpaRepository<BillPayment, Integer>{

	//BillPayment findOne(BillPayment payment);

}
