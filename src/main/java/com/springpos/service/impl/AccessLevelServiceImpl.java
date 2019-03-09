package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.AccessLevel;
import java.util.Optional;
import com.springpos.repository.AccessLevelRepository;
import com.springpos.service.AccessLevelService;

@Service
public class AccessLevelServiceImpl implements AccessLevelService {

    private AccessLevelRepository bizRepository;

    @Autowired
    public void setBizRepository(AccessLevelRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public AccessLevel save(AccessLevel entity) {
        return bizRepository.save(entity);
    }

    @Override
    public AccessLevel update(AccessLevel entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(AccessLevel entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public AccessLevel find(int id) {
        Optional<AccessLevel> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<AccessLevel> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<AccessLevel> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
