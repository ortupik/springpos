package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ServiceStatus;
import java.util.Optional;
import com.springpos.repository.ServiceStatusRepository;
import com.springpos.service.ServiceStatusService;

@Service
public class ServiceStatusServiceImpl implements ServiceStatusService {

    private ServiceStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(ServiceStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ServiceStatus save(ServiceStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ServiceStatus update(ServiceStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ServiceStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ServiceStatus find(int id) {
        Optional<ServiceStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ServiceStatus> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<ServiceStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
