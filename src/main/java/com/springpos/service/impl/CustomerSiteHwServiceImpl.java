package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.CustomerSiteHw;
import java.util.Optional;
import com.springpos.repository.CustomerSiteHwRepository;
import com.springpos.service.CustomerSiteHwService;

@Service
public class CustomerSiteHwServiceImpl implements CustomerSiteHwService {

    private CustomerSiteHwRepository bizRepository;

    @Autowired
    public void setBizRepository(CustomerSiteHwRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public CustomerSiteHw save(CustomerSiteHw entity) {
        return bizRepository.save(entity);
    }

    @Override
    public CustomerSiteHw update(CustomerSiteHw entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(CustomerSiteHw entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public CustomerSiteHw find(int id) {
        Optional<CustomerSiteHw> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<CustomerSiteHw> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<CustomerSiteHw> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
