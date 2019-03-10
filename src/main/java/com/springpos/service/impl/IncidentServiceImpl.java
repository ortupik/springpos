package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.Incident;
import java.util.Optional;
import com.springpos.repository.IncidentRepository;
import com.springpos.service.IncidentService;

@Service
public class IncidentServiceImpl implements IncidentService {

    private IncidentRepository bizRepository;

    @Autowired
    public void setBizRepository(IncidentRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public Incident save(Incident entity) {
        return bizRepository.save(entity);
    }

    @Override
    public Incident update(Incident entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(Incident entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public Incident find(int id) {
        Optional<Incident> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<Incident> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<Incident> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
