package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.PhotoType;
import java.util.Optional;
import com.springpos.repository.PhotoTypeRepository;
import com.springpos.service.PhotoTypeService;

@Service
public class PhotoTypeServiceImpl implements PhotoTypeService {

    private PhotoTypeRepository bizRepository;

    @Autowired
    public void setBizRepository(PhotoTypeRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public PhotoType save(PhotoType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public PhotoType update(PhotoType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(PhotoType entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public PhotoType find(int id) {
        Optional<PhotoType> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<PhotoType> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<PhotoType> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
