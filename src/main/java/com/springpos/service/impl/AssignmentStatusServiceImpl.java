package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.AssignmentStatus;
import java.util.Optional;
import com.springpos.repository.AssignmentStatusRepository;
import com.springpos.service.AssignmentStatusService;

@Service
public class AssignmentStatusServiceImpl implements AssignmentStatusService {

    private AssignmentStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(AssignmentStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public AssignmentStatus save(AssignmentStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public AssignmentStatus update(AssignmentStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(AssignmentStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public AssignmentStatus find(int id) {
        Optional<AssignmentStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<AssignmentStatus> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<AssignmentStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
