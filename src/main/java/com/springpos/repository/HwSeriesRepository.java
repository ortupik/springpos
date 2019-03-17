package com.springpos.repository;

import com.springpos.bean.HwSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface HwSeriesRepository extends JpaRepository<HwSeries, Integer> {

}
