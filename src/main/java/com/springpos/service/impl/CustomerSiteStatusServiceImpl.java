package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.CustomerSiteStatus;
import java.util.Optional;
import com.springpos.repository.CustomerSiteStatusRepository;
import com.springpos.service.CustomerSiteStatusService;

@Service
public class CustomerSiteStatusServiceImpl implements CustomerSiteStatusService {

    private CustomerSiteStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(CustomerSiteStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public CustomerSiteStatus save(CustomerSiteStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public CustomerSiteStatus update(CustomerSiteStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(CustomerSiteStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public CustomerSiteStatus find(int id) {
        Optional<CustomerSiteStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<CustomerSiteStatus> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<CustomerSiteStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
