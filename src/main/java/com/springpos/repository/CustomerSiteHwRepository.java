package com.springpos.repository;

import com.springpos.bean.CustomerSiteHw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface CustomerSiteHwRepository extends JpaRepository<CustomerSiteHw, Integer> {

}
