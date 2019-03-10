package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.RequiredSkill;
import java.util.Optional;
import com.springpos.repository.RequiredSkillRepository;
import com.springpos.service.RequiredSkillService;

@Service
public class RequiredSkillServiceImpl implements RequiredSkillService {

    private RequiredSkillRepository bizRepository;

    @Autowired
    public void setBizRepository(RequiredSkillRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public RequiredSkill save(RequiredSkill entity) {
        return bizRepository.save(entity);
    }

    @Override
    public RequiredSkill update(RequiredSkill entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(RequiredSkill entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public RequiredSkill find(int id) {
        Optional<RequiredSkill> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<RequiredSkill> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<RequiredSkill> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
