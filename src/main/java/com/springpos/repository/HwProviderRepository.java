package com.springpos.repository;

import com.springpos.bean.HwProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface HwProviderRepository extends JpaRepository<HwProvider, Integer> {

}
