package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwInventory;
import java.util.Optional;
import com.springpos.repository.HwInventoryRepository;
import com.springpos.service.HwInventoryService;

@Service
public class HwInventoryServiceImpl implements HwInventoryService {

    private HwInventoryRepository bizRepository;

    @Autowired
    public void setBizRepository(HwInventoryRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwInventory save(HwInventory entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwInventory update(HwInventory entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwInventory entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwInventory find(int id) {
        Optional<HwInventory> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwInventory> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<HwInventory> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
