package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.KnowledgeBaseCategory;
import java.util.Optional;
import com.springpos.repository.KnowledgeBaseCategoryRepository;
import com.springpos.service.KnowledgeBaseCategoryService;

@Service
public class KnowledgeBaseCategoryServiceImpl implements KnowledgeBaseCategoryService {

    private KnowledgeBaseCategoryRepository bizRepository;

    @Autowired
    public void setBizRepository(KnowledgeBaseCategoryRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public KnowledgeBaseCategory save(KnowledgeBaseCategory entity) {
        return bizRepository.save(entity);
    }

    @Override
    public KnowledgeBaseCategory update(KnowledgeBaseCategory entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(KnowledgeBaseCategory entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public KnowledgeBaseCategory find(int id) {
        Optional<KnowledgeBaseCategory> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<KnowledgeBaseCategory> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<KnowledgeBaseCategory> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
