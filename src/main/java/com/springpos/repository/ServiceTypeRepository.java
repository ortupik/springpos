package com.springpos.repository;

import com.springpos.bean.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface ServiceTypeRepository  extends JpaRepository<ServiceType, Integer>{
 
}
