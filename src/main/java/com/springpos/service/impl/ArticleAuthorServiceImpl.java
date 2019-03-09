package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ArticleAuthor;
import java.util.Optional;
import com.springpos.repository.ArticleAuthorRepository;
import com.springpos.service.ArticleAuthorService;

@Service
public class ArticleAuthorServiceImpl implements ArticleAuthorService {

    private ArticleAuthorRepository bizRepository;

    @Autowired
    public void setBizRepository(ArticleAuthorRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ArticleAuthor save(ArticleAuthor entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ArticleAuthor update(ArticleAuthor entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ArticleAuthor entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ArticleAuthor find(int id) {
        Optional<ArticleAuthor> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ArticleAuthor> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<ArticleAuthor> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
