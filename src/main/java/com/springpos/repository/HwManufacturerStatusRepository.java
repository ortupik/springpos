package com.springpos.repository;

import com.springpos.bean.HwManufacturerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface HwManufacturerStatusRepository extends JpaRepository<HwManufacturerStatus,Integer>{

    
}
