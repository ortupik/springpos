package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.Hw;
import java.util.Optional;
import com.springpos.repository.HwRepository;
import com.springpos.service.HwService;

@Service
public class HwServiceImpl implements HwService {

    private HwRepository bizRepository;

    @Autowired
    public void setBizRepository(HwRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public Hw save(Hw entity) {
        return bizRepository.save(entity);
    }

    @Override
    public Hw update(Hw entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(Hw entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public Hw find(int id) {
        Optional<Hw> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<Hw> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<Hw> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
