package com.rahpouyan.rahayi.demo.model.service;

import com.rahpouyan.rahayi.demo.model.entity.User;
import com.rahpouyan.rahayi.demo.model.repository.UserDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDA userDA;


    public void save(User user) {
        userDA.save(user);
    }


    public User findByUsername(String username) {
        return userDA.findByUsename(username);
    }

    public List<User> findAll() {
        return userDA.findAll();
    }

}
