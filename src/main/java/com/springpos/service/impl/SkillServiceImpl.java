package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.Skill;
import java.util.Optional;
import com.springpos.repository.SkillRepository;
import com.springpos.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {

    private SkillRepository bizRepository;

    @Autowired
    public void setBizRepository(SkillRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public Skill save(Skill entity) {
        return bizRepository.save(entity);
    }

    @Override
    public Skill update(Skill entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(Skill entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public Skill find(int id) {
        Optional<Skill> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<Skill> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<Skill> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
