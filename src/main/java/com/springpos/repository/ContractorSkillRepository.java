package com.springpos.repository;

import com.springpos.bean.ContractorSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface ContractorSkillRepository extends JpaRepository<ContractorSkill,Integer>{
     
}
