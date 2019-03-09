package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ServiceOrderPhoto;
import java.util.Optional;
import com.springpos.repository.ServiceOrderPhotoRepository;
import com.springpos.service.ServiceOrderPhotoService;

@Service
public class ServiceOrderPhotoServiceImpl implements ServiceOrderPhotoService {

    private ServiceOrderPhotoRepository bizRepository;

    @Autowired
    public void setBizRepository(ServiceOrderPhotoRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ServiceOrderPhoto save(ServiceOrderPhoto entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ServiceOrderPhoto update(ServiceOrderPhoto entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ServiceOrderPhoto entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ServiceOrderPhoto find(int id) {
        Optional<ServiceOrderPhoto> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ServiceOrderPhoto> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<ServiceOrderPhoto> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
