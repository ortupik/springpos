package com.springpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springpos.bean.Years;
import java.util.List;

@Repository
public interface YearsRepository extends JpaRepository<Years, Integer> {

    List<Years> findByYears(int years);
}
