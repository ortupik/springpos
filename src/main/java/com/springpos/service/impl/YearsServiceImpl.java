package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.Years;
import com.springpos.repository.YearsRepository;
import com.springpos.service.YearsService;
import java.util.Optional;

@Service
public class YearsServiceImpl implements YearsService {

    private YearsRepository bizRepository;

    @Autowired
    public void setBizRepository(YearsRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public Years save(Years entity) {
        return bizRepository.save(entity);
    }

    @Override
    public Years update(Years entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(Years entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public Years find(int id) {
        Optional<Years> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<Years> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public List<Years> findByYears(int yearsName) {
        return bizRepository.findByYears(yearsName);
    }

    @Override
    public void deleteInBatch(List<Years> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
