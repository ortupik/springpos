package com.springpos.repository;

import com.springpos.bean.CustomerSiteHwStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface CustomerSiteHwStatusRepository  extends JpaRepository<CustomerSiteHwStatus, Integer>{
    
}
