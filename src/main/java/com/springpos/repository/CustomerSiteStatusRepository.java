package com.springpos.repository;
import com.springpos.bean.CustomerSiteStatus; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface CustomerSiteStatusRepository  extends JpaRepository<CustomerSiteStatus, Object>{
  
}
