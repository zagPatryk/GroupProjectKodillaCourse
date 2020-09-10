package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    public User getUser (final Long id){
        Optional <User> user = userDao.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public User saveUser(final User user){
        return userDao.save(user);
    }

    public void deleteUser(final Long id){
        userDao.deleteById(id);
    }

    public long createUserKey(){
        Long generatedUserKey = (System.currentTimeMillis() / 1000);
        return generatedUserKey;
    }
}