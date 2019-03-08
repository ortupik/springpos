package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.ContactStatus;
import java.util.Optional;
import com.springpos.repository.ContactStatusRepository;
import com.springpos.service.ContactStatusService;

@Service
public class ContactStatusServiceImpl implements ContactStatusService {

    private ContactStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(ContactStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public ContactStatus save(ContactStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public ContactStatus update(ContactStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(ContactStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public ContactStatus find(int id) {
        Optional<ContactStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<ContactStatus> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<ContactStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
