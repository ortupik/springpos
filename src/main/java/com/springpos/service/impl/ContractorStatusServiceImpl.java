package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ContractorStatus;
import java.util.Optional;
import com.springpos.repository.ContractorStatusRepository;
import com.springpos.service.ContractorStatusService;

@Service
public class ContractorStatusServiceImpl implements ContractorStatusService {

    private ContractorStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(ContractorStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ContractorStatus save(ContractorStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ContractorStatus update(ContractorStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ContractorStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ContractorStatus find(int id) {
        Optional<ContractorStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ContractorStatus> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<ContractorStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
