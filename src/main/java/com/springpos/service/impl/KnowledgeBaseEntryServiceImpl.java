package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.KnowledgeBaseEntry;
import java.util.Optional;
import com.springpos.repository.KnowledgeBaseEntryRepository;
import com.springpos.service.KnowledgeBaseEntryService;

@Service
public class KnowledgeBaseEntryServiceImpl implements KnowledgeBaseEntryService {

    private KnowledgeBaseEntryRepository bizRepository;

    @Autowired
    public void setBizRepository(KnowledgeBaseEntryRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public KnowledgeBaseEntry save(KnowledgeBaseEntry entity) {
        return bizRepository.save(entity);
    }

    @Override
    public KnowledgeBaseEntry update(KnowledgeBaseEntry entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(KnowledgeBaseEntry entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public KnowledgeBaseEntry find(int id) {
        Optional<KnowledgeBaseEntry> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<KnowledgeBaseEntry> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<KnowledgeBaseEntry> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
