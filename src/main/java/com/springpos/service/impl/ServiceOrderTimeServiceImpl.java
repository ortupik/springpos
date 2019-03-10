package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ServiceOrderLine;
import java.util.Optional;
import com.springpos.repository.ServiceOrderLineRepository;
import com.springpos.service.ServiceOrderLineService;

@Service
public class ServiceOrderTimeServiceImpl implements ServiceOrderLineService {

    private ServiceOrderLineRepository bizRepository;

    @Autowired
    public void setBizRepository(ServiceOrderLineRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ServiceOrderLine save(ServiceOrderLine entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ServiceOrderLine update(ServiceOrderLine entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ServiceOrderLine entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ServiceOrderLine find(int id) {
        Optional<ServiceOrderLine> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ServiceOrderLine> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<ServiceOrderLine> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
