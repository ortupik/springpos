package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.CustomerSite;
import java.util.Optional;
import com.springpos.repository.CustomerSiteRepository;
import com.springpos.service.CustomerSiteService;

@Service
public class CustomerSiteServiceImpl implements CustomerSiteService {

    private CustomerSiteRepository bizRepository;

    @Autowired
    public void setBizRepository(CustomerSiteRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public CustomerSite save(CustomerSite entity) {
        return bizRepository.save(entity);
    }

    @Override
    public CustomerSite update(CustomerSite entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(CustomerSite entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public CustomerSite find(int id) {
        Optional<CustomerSite> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<CustomerSite> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<CustomerSite> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
