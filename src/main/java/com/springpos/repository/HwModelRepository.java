package com.springpos.repository;

import com.springpos.bean.HwModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface HwModelRepository extends JpaRepository<HwModel, Integer> {

}
