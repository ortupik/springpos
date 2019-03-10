package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.IncidentType;
import java.util.Optional;
import com.springpos.repository.IncidentTypeRepository;
import com.springpos.service.IncidentTypeService;

@Service
public class IncidentTypeServiceImpl implements IncidentTypeService {

    private IncidentTypeRepository bizRepository;

    @Autowired
    public void setBizRepository(IncidentTypeRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public IncidentType save(IncidentType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public IncidentType update(IncidentType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(IncidentType entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public IncidentType find(int id) {
        Optional<IncidentType> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<IncidentType> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<IncidentType> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
