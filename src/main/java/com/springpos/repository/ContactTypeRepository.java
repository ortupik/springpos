package com.springpos.repository;

import com.springpos.bean.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType,Integer>{
    
}
