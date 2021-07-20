
package com.cg.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.entities.BenificiaryDetails;
import com.cg.entities.Wallet;

@Repository
public interface IBenificiaryRepository extends JpaRepository<BenificiaryDetails, String> {

	@Query("From BenificiaryDetails bd where bd.mobileNumber= ?1")
	public List<BenificiaryDetails> findBymobileNumber(String mobileNumber);

	@Transactional
	@Modifying
	@Query("delete from BenificiaryDetails bd where bd.wallet = ?1 and bd.mobileNumber = ?2 ")
	public int deleteById(Wallet walletId,String mobileNumber);
	
	@Query("From BenificiaryDetails bd where bd.wallet = ?1")
	public List<BenificiaryDetails> viewAllBenificiaryById(Wallet wallet);
	
	@Query("from BenificiaryDetails bd where bd.mobileNumber=?1 and bd.wallet=?2")
	public BenificiaryDetails getByMobAndWal(String mobileno,Wallet wallet);
}
