package com.springpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springpos.bean.Hw;

@Repository
public interface HwRepository extends JpaRepository<Hw, Integer> {

}
