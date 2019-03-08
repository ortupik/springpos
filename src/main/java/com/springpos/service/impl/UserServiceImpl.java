package com.springpos.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springpos.bean.User;
import com.springpos.repository.UserRepository;
import com.springpos.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository bizRepository;

    @Autowired
    public void setBizRepository(UserRepository bizRepository) {
        this.bizRepository = bizRepository;
    }

    @Override
    public User save(User entity) {
        return bizRepository.save(entity);
    }

    @Override
    public User update(User entity) {
        return bizRepository.save(entity);
    }

    @Override
    public void delete(User entity) {
        bizRepository.delete(entity);
    }

    @Override
    public void delete(int id) {
        bizRepository.deleteById(id);
    }

    @Override
    public User find(int id) {
        Optional<User> opdata = bizRepository.findById(id);
        return opdata.get();
    }

    @Override
    public List<User> findAll() {
        return bizRepository.findAll();
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = this.findByEmail(username);
        if (user == null) {
            return false;
        } else {
            return password.equals(user.getPassword());
        }
    }

    @Override
    public User findByEmail(String email) {
        return bizRepository.findByEmail(email);
    }

    @Override
    public void deleteInBatch(List<User> users) {
        bizRepository.deleteInBatch(users);
    }

}
