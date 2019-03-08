package com.springpos.repository;

import com.springpos.bean.ContactStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface ContactStatusRepository  extends JpaRepository<ContactStatus, Integer>{
      
}
