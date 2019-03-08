package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwProviderStatus;
import java.util.Optional;
import com.springpos.repository.HwProviderStatusRepository;
import com.springpos.service.HwProviderStatusService;

@Service
public class HwProviderStatusServiceImpl implements HwProviderStatusService {

    private HwProviderStatusRepository bizRepository;

    @Autowired
    public void setBizRepository(HwProviderStatusRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwProviderStatus save(HwProviderStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwProviderStatus update(HwProviderStatus entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwProviderStatus entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwProviderStatus find(int id) {
        Optional<HwProviderStatus> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwProviderStatus> findAll() {
        return bizRepository.findAll();
    }


    @Override
    public void deleteInBatch(List<HwProviderStatus> categories) {
        bizRepository.deleteInBatch(categories);
    }


}
