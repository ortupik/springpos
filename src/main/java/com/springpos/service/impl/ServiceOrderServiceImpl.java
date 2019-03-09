package com.springpos.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ServiceOrder;
import java.util.Optional;
import com.springpos.repository.ServiceOrderRepository;
import com.springpos.service.ServiceOrderService;

@Service
public class ServiceOrderServiceImpl implements ServiceOrderService {

    private ServiceOrderRepository bizRepository;

    @Autowired
    public void setBizRepository(ServiceOrderRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ServiceOrder save(ServiceOrder entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ServiceOrder update(ServiceOrder entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ServiceOrder entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ServiceOrder find(int id) {
        Optional<ServiceOrder> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ServiceOrder> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<ServiceOrder> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
