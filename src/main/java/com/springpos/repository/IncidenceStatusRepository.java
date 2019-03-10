package com.springpos.repository;

import com.springpos.bean.IncidenceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface IncidenceStatusRepository extends JpaRepository<IncidenceStatus, Integer> {

}
