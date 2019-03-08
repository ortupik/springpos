package com.springpos.repository;

import com.springpos.bean.IncidentContractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface IncidentContractorRepository extends JpaRepository<IncidentContractor,Integer>{
 
}
