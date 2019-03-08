package com.springpos.service;

import com.springpos.bean.User;
import com.springpos.generic.GenericService;

public interface UserService extends GenericService<User> {

    boolean authenticate(String email, String password);

    User findByEmail(String email);

}
