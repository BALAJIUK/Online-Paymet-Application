package com.cg.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entities.Customer;
import com.cg.entities.Wallet;

@Repository
public interface IUserRepository extends JpaRepository<Customer, String> {

	@Transactional
	@Modifying
	@Query("update Customer c set c.wallet=?1 where c.mobileNumber=?2")
	public void addWallet(Wallet w, String mobileNo);

	@Transactional
	@Modifying
	@Query("update Customer c set c.password=?1 where c.mobileNumber=?2")
	public int updatePassword(String pass, String mobile);

	@Query("select c.mobileNumber from Customer c where c.wallet=?1")
	public String getCustomer(Wallet wallet);

	@Query("from Customer c where c.mobileNumber=?1")
	public Customer getByMobileno(String mobile);

	@Query("from Customer c where c.wallet=?1")
	public Customer getByWallet(Wallet wallet);
}
