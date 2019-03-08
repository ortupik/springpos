package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwModelStatus;
import java.util.Optional;
import com.springpos.repository.HwModelStatusRepository;
import com.springpos.service.HwModelStatusService;

@Service
public class HwModelStatusServiceImpl implements HwModelStatusService {

    private HwModelStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(HwModelStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwModelStatus save(HwModelStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwModelStatus update(HwModelStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwModelStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwModelStatus find(int id) {
        Optional<HwModelStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwModelStatus> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<HwModelStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
