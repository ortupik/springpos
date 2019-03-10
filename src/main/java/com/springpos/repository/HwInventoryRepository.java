package com.springpos.repository;

import com.springpos.bean.HwInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface HwInventoryRepository extends JpaRepository<HwInventory, Integer> {

}
