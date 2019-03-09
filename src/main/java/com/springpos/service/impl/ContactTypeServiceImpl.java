package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ContactType;
import java.util.Optional;
import com.springpos.repository.ContactTypeRepository;
import com.springpos.service.ContactTypeService;

@Service
public class ContactTypeServiceImpl implements ContactTypeService {

    private ContactTypeRepository bizRepository;

    @Autowired
    public void setBizRepository(ContactTypeRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ContactType save(ContactType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ContactType update(ContactType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ContactType entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ContactType find(int id) {
        Optional<ContactType> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ContactType> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<ContactType> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
