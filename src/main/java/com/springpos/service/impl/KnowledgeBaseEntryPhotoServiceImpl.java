package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.KnowledgeBaseEntryPhoto;
import java.util.Optional;
import com.springpos.repository.KnowledgeBaseEntryPhotoRepository;
import com.springpos.service.KnowledgeBaseEntryPhotoService;

@Service
public class KnowledgeBaseEntryPhotoServiceImpl implements KnowledgeBaseEntryPhotoService {

    private KnowledgeBaseEntryPhotoRepository bizRepository;

    @Autowired
    public void setBizRepository(KnowledgeBaseEntryPhotoRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public KnowledgeBaseEntryPhoto save(KnowledgeBaseEntryPhoto entity) {
        return bizRepository.save(entity);
    }

    @Override
    public KnowledgeBaseEntryPhoto update(KnowledgeBaseEntryPhoto entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(KnowledgeBaseEntryPhoto entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public KnowledgeBaseEntryPhoto find(int id) {
        Optional<KnowledgeBaseEntryPhoto> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<KnowledgeBaseEntryPhoto> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<KnowledgeBaseEntryPhoto> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
