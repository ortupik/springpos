package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.Contact;
import java.util.Optional;
import com.springpos.repository.ContactRepository;
import com.springpos.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository bizRepository;

    @Autowired
    public void setBizRepository(ContactRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public Contact save(Contact entity) {
        return bizRepository.save(entity);
    }

    @Override
    public Contact update(Contact entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(Contact entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public Contact find(int id) {
        Optional<Contact> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<Contact> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<Contact> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
