package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.Payment;
import java.util.Optional;
import com.springpos.repository.PaymentRepository;
import com.springpos.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository bizRepository;

    @Autowired
    public void setBizRepository(PaymentRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public Payment save(Payment entity) {
        return bizRepository.save(entity);
    }

    @Override
    public Payment update(Payment entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(Payment entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public Payment find(int id) {
        Optional<Payment> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<Payment> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<Payment> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
