package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ContractorSkill;
import java.util.Optional;
import com.springpos.repository.ContractorSkillRepository;
import com.springpos.service.ContractorSkillService;

@Service
public class ContractorSkillServiceImpl implements ContractorSkillService {

    private ContractorSkillRepository bizRepository;

    @Autowired
    public void setBizRepository(ContractorSkillRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ContractorSkill save(ContractorSkill entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ContractorSkill update(ContractorSkill entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ContractorSkill entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ContractorSkill find(int id) {
        Optional<ContractorSkill> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ContractorSkill> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<ContractorSkill> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
