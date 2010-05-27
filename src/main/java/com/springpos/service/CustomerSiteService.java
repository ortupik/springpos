package com.springpos.service;

import com.springpos.bean.CustomerSite;
import com.springpos.generic.GenericService;

public interface CustomerSiteService extends GenericService<CustomerSite> {

    public CustomerSite findByCust_site_name(String cust_site_name);

}
