
package com.cg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.BenificiaryDetails;
@Repository
public interface IBenificiaryRepository extends JpaRepository<BenificiaryDetails, String> {

}
