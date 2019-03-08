package com.springpos.repository;

import com.springpos.bean.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository 
public interface StateRepository extends JpaRepository<State,Integer>{
    
}
