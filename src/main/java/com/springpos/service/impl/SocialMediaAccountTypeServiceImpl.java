package com.springpos.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.SocialMediaAccountType;
import java.util.Optional;
import com.springpos.repository.SocialMediaAccountTypeRepository;
import com.springpos.service.SocialMediaAccountTypeService;

@Service
public class SocialMediaAccountTypeServiceImpl implements SocialMediaAccountTypeService {

    private SocialMediaAccountTypeRepository bizRepository;

    @Autowired
    public void setBizRepository(SocialMediaAccountTypeRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public SocialMediaAccountType save(SocialMediaAccountType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public SocialMediaAccountType update(SocialMediaAccountType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(SocialMediaAccountType entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public SocialMediaAccountType find(int id) {
        Optional<SocialMediaAccountType> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<SocialMediaAccountType> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<SocialMediaAccountType> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
