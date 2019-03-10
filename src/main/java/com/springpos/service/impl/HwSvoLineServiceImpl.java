package com.springpos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpos.bean.HwSvoLine;
import java.util.Optional;
import com.springpos.repository.HwSvoLineRepository;
import com.springpos.service.HwSvoLineService;

@Service
public class HwSvoLineServiceImpl implements HwSvoLineService {

    private HwSvoLineRepository bizRepository;

    @Autowired
    public void setBizRepository(HwSvoLineRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public HwSvoLine save(HwSvoLine entity) {
        return bizRepository.save(entity);
    }

    @Override
    public HwSvoLine update(HwSvoLine entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(HwSvoLine entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public HwSvoLine find(int id) {
        Optional<HwSvoLine> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<HwSvoLine> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public void deleteInBatch(List<HwSvoLine> categories) {
        bizRepository.deleteInBatch(categories);
    }

}
