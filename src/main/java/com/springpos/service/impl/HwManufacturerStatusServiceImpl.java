package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwManufacturerStatus;
import java.util.Optional;
import com.springpos.repository.HwManufacturerStatusRepository;
import com.springpos.service.HwManufacturerStatusService;

@Service
public class HwManufacturerStatusServiceImpl implements HwManufacturerStatusService {

    private HwManufacturerStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(HwManufacturerStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwManufacturerStatus save(HwManufacturerStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwManufacturerStatus update(HwManufacturerStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwManufacturerStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwManufacturerStatus find(int id) {
        Optional<HwManufacturerStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwManufacturerStatus> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<HwManufacturerStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
