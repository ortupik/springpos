package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwInventoryStatus;
import java.util.Optional;
import com.springpos.repository.HwInventoryStatusRepository;
import com.springpos.service.HwInventoryStatusService;

@Service
public class HwInventoryStatusServiceImpl implements HwInventoryStatusService {

    private HwInventoryStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(HwInventoryStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwInventoryStatus save(HwInventoryStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwInventoryStatus update(HwInventoryStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwInventoryStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwInventoryStatus find(int id) {
        Optional<HwInventoryStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwInventoryStatus> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<HwInventoryStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
