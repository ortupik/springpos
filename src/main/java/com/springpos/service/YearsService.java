package com.springpos.service;

import com.springpos.bean.Years;
import com.springpos.generic.GenericService;
import java.util.List;

public interface YearsService extends GenericService<Years> {

    List<Years> findByYears(int years);
}
