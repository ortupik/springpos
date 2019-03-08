package com.springpos.repository;

import com.springpos.bean.HwManufacturer; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface HwManufacturerRepository extends JpaRepository<HwManufacturer,Integer>{
  
}
