package com.springpos.repository;

import com.springpos.bean.SocialMediaAccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface SocialMediaAccountStatusRepository extends JpaRepository<SocialMediaAccountStatus,Integer>{
  
}
