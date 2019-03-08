package com.springpos.repository;

import com.springpos.bean.ServiceOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface ServiceOrderLineRepository  extends JpaRepository<ServiceOrderLine, Integer>{
    
}
