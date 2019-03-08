package com.springpos.repository;

import com.springpos.bean.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType,Integer>{
   
}
