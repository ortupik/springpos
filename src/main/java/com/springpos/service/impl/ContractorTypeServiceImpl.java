package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ContractorType;
import java.util.Optional;
import com.springpos.repository.ContractorTypeRepository;
import com.springpos.service.ContractorTypeService;

@Service
public class ContractorTypeServiceImpl implements ContractorTypeService {

    private ContractorTypeRepository bizRepository;

    @Autowired
    public void setBizRepository(ContractorTypeRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ContractorType save(ContractorType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ContractorType update(ContractorType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ContractorType entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ContractorType find(int id) {
        Optional<ContractorType> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ContractorType> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<ContractorType> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
