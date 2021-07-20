package com.cg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.entities.CustomerToken;

public interface CustomerTokenRepository extends JpaRepository<CustomerToken ,String>{

	@Query("from CustomerToken ct where ct.mobileNo=?1")
	public CustomerToken getByMobileNo(String mobile);
}
