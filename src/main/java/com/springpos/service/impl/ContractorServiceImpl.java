package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.Contractor;
import java.util.Optional;
import com.springpos.repository.ContractorRepository;
import com.springpos.service.ContractorService;

@Service
public class ContractorServiceImpl implements ContractorService {

    private ContractorRepository bizRepository;

    @Autowired
    public void setBizRepository(ContractorRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public Contractor save(Contractor entity) {
        return bizRepository.save(entity);
    }

    @Override
    public Contractor update(Contractor entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(Contractor entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public Contractor find(int id) {
        Optional<Contractor> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<Contractor> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<Contractor> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
