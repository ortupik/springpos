package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwType;
import java.util.Optional;
import com.springpos.repository.HwTypeRepository;
import com.springpos.service.HwTypeService;

@Service
public class HwTypeServiceImpl implements HwTypeService {

    private HwTypeRepository bizRepository;

    @Autowired
    public void setBizRepository(HwTypeRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwType save(HwType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwType update(HwType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwType entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwType find(int id) {
        Optional<HwType> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwType> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<HwType> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
