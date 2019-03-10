package com.springpos.repository;

import com.springpos.bean.ContractorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface ContractorTypeRepository extends JpaRepository<ContractorType, Integer> {

}
