package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.PaymentStatus;
import java.util.Optional;
import com.springpos.repository.PaymentStatusRepository;
import com.springpos.service.PaymentStatusService;

@Service
public class PaymentStatusServiceImpl implements PaymentStatusService {

    private PaymentStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(PaymentStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public PaymentStatus save(PaymentStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public PaymentStatus update(PaymentStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(PaymentStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public PaymentStatus find(int id) {
        Optional<PaymentStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<PaymentStatus> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<PaymentStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
