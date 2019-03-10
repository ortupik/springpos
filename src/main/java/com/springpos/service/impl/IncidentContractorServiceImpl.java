package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.IncidentContractor;
import java.util.Optional;
import com.springpos.repository.IncidentContractorRepository;
import com.springpos.service.IncidentContractorService;

@Service
public class IncidentContractorServiceImpl implements IncidentContractorService {

    private IncidentContractorRepository bizRepository;

    @Autowired
    public void setBizRepository(IncidentContractorRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public IncidentContractor save(IncidentContractor entity) {
        return bizRepository.save(entity);
    }

    @Override
    public IncidentContractor update(IncidentContractor entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(IncidentContractor entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public IncidentContractor find(int id) {
        Optional<IncidentContractor> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<IncidentContractor> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<IncidentContractor> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
