package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.PaymentType;
import java.util.Optional;
import com.springpos.repository.PaymentTypeRepository;
import com.springpos.service.PaymentTypeService;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private PaymentTypeRepository bizRepository;

    @Autowired
    public void setBizRepository(PaymentTypeRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public PaymentType save(PaymentType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public PaymentType update(PaymentType entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(PaymentType entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public PaymentType find(int id) {
        Optional<PaymentType> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<PaymentType> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<PaymentType> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
