package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.Assignment;
import java.util.Optional;
import com.springpos.repository.AssignmentRepository;
import com.springpos.service.AssignmentService;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private AssignmentRepository bizRepository;

    @Autowired
    public void setBizRepository(AssignmentRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public Assignment save(Assignment entity) {
        return bizRepository.save(entity);
    }

    @Override
    public Assignment update(Assignment entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(Assignment entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public Assignment find(int id) {
        Optional<Assignment> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<Assignment> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<Assignment> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
