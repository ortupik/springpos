package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwManufacturer;
import java.util.Optional;
import com.springpos.repository.HwManufacturerRepository;
import com.springpos.service.HwManufacturerService;

@Service
public class HwManufacturerServiceImpl implements HwManufacturerService {

    private HwManufacturerRepository bizRepository;

    @Autowired
    public void setBizRepository(HwManufacturerRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwManufacturer save(HwManufacturer entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwManufacturer update(HwManufacturer entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwManufacturer entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwManufacturer find(int id) {
        Optional<HwManufacturer> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwManufacturer> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<HwManufacturer> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
