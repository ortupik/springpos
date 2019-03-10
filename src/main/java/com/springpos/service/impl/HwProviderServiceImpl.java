package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwProvider;
import java.util.Optional;
import com.springpos.repository.HwProviderRepository;
import com.springpos.service.HwProviderService;

@Service
public class HwProviderServiceImpl implements HwProviderService {

    private HwProviderRepository bizRepository;

    @Autowired
    public void setBizRepository(HwProviderRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwProvider save(HwProvider entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwProvider update(HwProvider entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwProvider entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwProvider find(int id) {
        Optional<HwProvider> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwProvider> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<HwProvider> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
