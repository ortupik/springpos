package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.CustomerSiteType;
import java.util.Optional;
import com.springpos.repository.CustomerSiteTypeRepository;
import com.springpos.service.CustomerSiteTypeService;

@Service
public class CustomerSiteTypeServiceImpl implements CustomerSiteTypeService {

    private CustomerSiteTypeRepository bizRepository;

    @Autowired
    public void setBizRepository(CustomerSiteTypeRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public CustomerSiteType save(CustomerSiteType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public CustomerSiteType update(CustomerSiteType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(CustomerSiteType entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public CustomerSiteType find(int id) {
        Optional<CustomerSiteType> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<CustomerSiteType> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<CustomerSiteType> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
