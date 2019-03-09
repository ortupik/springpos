package com.springpos.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ServiceType;
import java.util.Optional;
import com.springpos.repository.ServiceTypeRepository;
import com.springpos.service.ServiceTypeService;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private ServiceTypeRepository bizRepository;

    @Autowired
    public void setBizRepository(ServiceTypeRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ServiceType save(ServiceType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ServiceType update(ServiceType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ServiceType entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ServiceType find(int id) {
        Optional<ServiceType> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ServiceType> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<ServiceType> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
