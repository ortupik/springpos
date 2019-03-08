package com.springpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springpos.bean.HwSvoLine;

/**
 *

 */
@Repository
public interface HwSvoLineRepository extends JpaRepository<HwSvoLine,Integer>{
  
}
