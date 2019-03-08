package com.springpos.repository;
import com.springpos.bean.CustomerSite; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface CustomerSiteRepository  extends JpaRepository<CustomerSite, Integer>{
     
}
