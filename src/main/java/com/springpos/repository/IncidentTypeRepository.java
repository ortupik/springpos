package com.springpos.repository;

import com.springpos.bean.IncidentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface IncidentTypeRepository extends JpaRepository<IncidentType, Integer> {

}
