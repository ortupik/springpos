package com.springpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springpos.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    User findByEmail(String email);
}
