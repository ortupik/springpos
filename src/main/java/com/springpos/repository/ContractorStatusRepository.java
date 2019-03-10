package com.springpos.repository;

import com.springpos.bean.ContractorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface ContractorStatusRepository extends JpaRepository<ContractorStatus, Integer> {

}
