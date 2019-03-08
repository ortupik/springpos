package com.springpos.repository;

import com.springpos.bean.ServiceOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface ServiceOrderStatusRepository extends JpaRepository<ServiceOrderStatus,Integer>{
   
}
