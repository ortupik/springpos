package com.springpos.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.SocialMediaAccount;
import java.util.Optional;
import com.springpos.repository.SocialMediaAccountRepository;
import com.springpos.service.SocialMediaAccountService;

@Service
public class SocialMediaAccountServiceImpl implements SocialMediaAccountService {

    private SocialMediaAccountRepository bizRepository;

    @Autowired
    public void setBizRepository(SocialMediaAccountRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public SocialMediaAccount save(SocialMediaAccount entity) {
        return bizRepository.save(entity);
    }

    @Override
    public SocialMediaAccount update(SocialMediaAccount entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(SocialMediaAccount entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public SocialMediaAccount find(int id) {
        Optional<SocialMediaAccount> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<SocialMediaAccount> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<SocialMediaAccount> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
