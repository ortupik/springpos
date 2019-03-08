package com.springpos.repository;

import com.springpos.bean.SocialMediaAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface SocialMediaAccountRepository extends JpaRepository<SocialMediaAccount,Integer>{
   
}
