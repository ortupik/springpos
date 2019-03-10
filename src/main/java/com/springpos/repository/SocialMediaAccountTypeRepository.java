package com.springpos.repository;

import com.springpos.bean.SocialMediaAccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface SocialMediaAccountTypeRepository extends JpaRepository<SocialMediaAccountType, Integer> {

}
