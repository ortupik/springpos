package com.springpos.repository;

import com.springpos.bean.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface ContractorRepository  extends JpaRepository<Contractor, Integer>{
  
}
