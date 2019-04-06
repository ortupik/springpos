package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.Country;
import java.util.Optional;
import com.springpos.repository.CountryRepository;
import com.springpos.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryRepository bizRepository;

    @Autowired
    public void setBizRepository(CountryRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public Country save(Country entity) {
        return bizRepository.save(entity);
    }

    @Override
    public Country update(Country entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(Country entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public Country find(int id) {
        Optional<Country> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<Country> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<Country> country) {
        bizRepository.deleteInBatch(country);
    }

    @Override
    public Country findCountry(String united_States) {
    return bizRepository.findByCountryName(united_States);
    }

}
