package com.springpos.service;

import com.springpos.bean.Country;
import com.springpos.generic.GenericService;

public interface CountryService extends GenericService<Country> {

    public Object findCountry(String united_States);

}
