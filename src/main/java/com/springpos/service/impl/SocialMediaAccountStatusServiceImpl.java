package com.springpos.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.SocialMediaAccountStatus;
import java.util.Optional;
import com.springpos.repository.SocialMediaAccountStatusRepository;
import com.springpos.service.SocialMediaAccountStatusService;

@Service
public class SocialMediaAccountStatusServiceImpl implements SocialMediaAccountStatusService {

    private SocialMediaAccountStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(SocialMediaAccountStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public SocialMediaAccountStatus save(SocialMediaAccountStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public SocialMediaAccountStatus update(SocialMediaAccountStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(SocialMediaAccountStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public SocialMediaAccountStatus find(int id) {
        Optional<SocialMediaAccountStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<SocialMediaAccountStatus> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<SocialMediaAccountStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
