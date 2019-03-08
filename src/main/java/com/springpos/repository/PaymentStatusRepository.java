package com.springpos.repository;

import com.springpos.bean.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface PaymentStatusRepository  extends JpaRepository<PaymentStatus, Integer>{
 
}
