package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ServiceOrderStatus;
import java.util.Optional;
import com.springpos.repository.ServiceOrderStatusRepository;
import com.springpos.service.ServiceOrderStatusService;

@Service
public class ServiceOrderStatusServiceImpl implements ServiceOrderStatusService {

    private ServiceOrderStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(ServiceOrderStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ServiceOrderStatus save(ServiceOrderStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ServiceOrderStatus update(ServiceOrderStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ServiceOrderStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ServiceOrderStatus find(int id) {
        Optional<ServiceOrderStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ServiceOrderStatus> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<ServiceOrderStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
