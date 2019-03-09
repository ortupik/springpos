package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.IncidenceStatus;
import java.util.Optional;
import com.springpos.repository.IncidenceStatusRepository;
import com.springpos.service.IncidenceStatusService;

@Service
public class IncidenceStatusServiceImpl implements IncidenceStatusService {

    private IncidenceStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(IncidenceStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public IncidenceStatus save(IncidenceStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public IncidenceStatus update(IncidenceStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(IncidenceStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public IncidenceStatus find(int id) {
        Optional<IncidenceStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<IncidenceStatus> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<IncidenceStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
