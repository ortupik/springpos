/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springpos.repository;

import com.springpos.bean.AssignmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository 
public interface AssignmentStatusRepository  extends JpaRepository<AssignmentStatus, Integer>{
    
    
}
