package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwSeriesStatus;
import java.util.Optional;
import com.springpos.repository.HwSeriesStatusRepository;
import com.springpos.service.HwSeriesStatusService;

@Service
public class HwSeriesStatusServiceImpl implements HwSeriesStatusService {

    private HwSeriesStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(HwSeriesStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwSeriesStatus save(HwSeriesStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwSeriesStatus update(HwSeriesStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwSeriesStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwSeriesStatus find(int id) {
        Optional<HwSeriesStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwSeriesStatus> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<HwSeriesStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
