package com.springpos.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.State;
import java.util.Optional;
import com.springpos.repository.StateRepository;
import com.springpos.service.StateService;

@Service
public class StateServiceImpl implements StateService {

    private StateRepository bizRepository;

    @Autowired
    public void setBizRepository(StateRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public State save(State entity) {
        return bizRepository.save(entity);
    }

    @Override
    public State update(State entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(State entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public State find(int id) {
        Optional<State> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<State> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<State> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
