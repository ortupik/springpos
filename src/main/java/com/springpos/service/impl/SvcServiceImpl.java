package com.springpos.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.Svc;
import java.util.Optional;
import com.springpos.repository.SvcRepository;
import com.springpos.service.SvcService;

@Service
public class SvcServiceImpl implements SvcService {

    private SvcRepository bizRepository;

    @Autowired
    public void setBizRepository(SvcRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public Svc save(Svc entity) {
        return bizRepository.save(entity);
    }

    @Override
    public Svc update(Svc entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(Svc entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public Svc find(int id) {
        Optional<Svc> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<Svc> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<Svc> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
