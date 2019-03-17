package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwSeries;
import java.util.Optional;
import com.springpos.repository.HwSeriesRepository;
import com.springpos.service.HwSeriesService;

@Service
public class HwSeriesServiceImpl implements HwSeriesService {

    private HwSeriesRepository bizRepository;

    @Autowired
    public void setBizRepository(HwSeriesRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwSeries save(HwSeries entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwSeries update(HwSeries entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwSeries entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwSeries find(int id) {
        Optional<HwSeries> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwSeries> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<HwSeries> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
