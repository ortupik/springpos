package com.springpos.repository;

import com.springpos.bean.ServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface ServiceStatusRepository extends JpaRepository<ServiceStatus, Integer> {

}
