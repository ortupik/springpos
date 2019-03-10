package com.springpos.repository;

import com.springpos.bean.HwModelStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface HwModelStatusRepository extends JpaRepository<HwModelStatus, Integer> {

}
