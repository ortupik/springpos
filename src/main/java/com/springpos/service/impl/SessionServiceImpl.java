package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.Sessions;
import com.springpos.repository.SessionRepository;
import com.springpos.service.SessionService;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository bizRepository;

    @Autowired
    public void setBizRepository(SessionRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public Sessions save(Sessions entity) {
        return bizRepository.save(entity);
    }

    @Override
    public Sessions update(Sessions entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(Sessions entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public Sessions find(int id) {
        Optional<Sessions> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<Sessions> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public Sessions findBySessionName(String categoryName) {
        return bizRepository.findBySessionName(categoryName);
    }

    @Override
    public void deleteInBatch(List<Sessions> categories) {
        bizRepository.deleteInBatch(categories);
    }

    @Override
    public Sessions findBySessionLatest() {
        return bizRepository.findAll().get(0);
    }

}
