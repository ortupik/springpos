package com.springpos.repository;

import com.springpos.bean.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer> {

}
