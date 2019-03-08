package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwModel;
import java.util.Optional;
import com.springpos.repository.HwModelRepository;
import com.springpos.service.HwModelService;

@Service
public class HwModelServiceImpl implements HwModelService {

    private HwModelRepository bizRepository;

    @Autowired
    public void setBizRepository(HwModelRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwModel save(HwModel entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwModel update(HwModel entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwModel entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwModel find(int id) {
        Optional<HwModel> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwModel> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<HwModel> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
