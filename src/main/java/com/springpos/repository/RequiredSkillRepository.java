package com.springpos.repository;

import com.springpos.bean.RequiredSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface RequiredSkillRepository extends JpaRepository<RequiredSkill, Integer> {

}
