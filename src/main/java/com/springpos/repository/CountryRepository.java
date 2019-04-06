package com.springpos.repository;

import com.springpos.bean.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
Country findByCountryName(String countryName);
}
