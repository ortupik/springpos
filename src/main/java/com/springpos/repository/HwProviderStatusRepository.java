package com.springpos.repository;

import com.springpos.bean.HwProviderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface HwProviderStatusRepository extends JpaRepository<HwProviderStatus, Integer> {

}
