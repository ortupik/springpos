package com.springpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springpos.bean.Sessions;

@Repository
public interface SessionRepository extends JpaRepository<Sessions, Integer> {

    public Sessions findBySessionName(String categoryName);

}
