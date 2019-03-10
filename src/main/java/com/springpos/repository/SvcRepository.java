package com.springpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springpos.bean.Svc;

@Repository
public interface SvcRepository extends JpaRepository<Svc, Integer> {

}
