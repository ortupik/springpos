
package com.springpos.repository;

import com.springpos.bean.HwInventoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface HwInventoryStatusRepository extends JpaRepository<HwInventoryStatus,Integer>{
   
}
