package com.springpos.service;

import com.springpos.bean.Sessions;
import com.springpos.generic.GenericService;

public interface SessionService extends GenericService<Sessions> {

    Sessions findBySessionName(String sessionName);

    Sessions findBySessionLatest();
}
