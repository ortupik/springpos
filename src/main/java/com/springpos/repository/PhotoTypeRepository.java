package com.springpos.repository;

import com.springpos.bean.PhotoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface PhotoTypeRepository  extends JpaRepository<PhotoType, Integer>{
     
}
