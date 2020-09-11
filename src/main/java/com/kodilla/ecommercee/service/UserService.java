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
        if (userDao.findById(id).isPresent()){
            return userDao.findById(id).get();
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

    public long createUserKey(long userId){
        User user = getUser(userId);
        long generatedUserKey = System.currentTimeMillis() / 1000;
        user.setUserKey(generatedUserKey);
        saveUser(user);
        return generatedUserKey;
    }
     public boolean checkIfUserKeyIsValid(long userId){
        User user = getUser(userId);
        return (System.currentTimeMillis()/1000) - user.getUserKey() < 30;
     }
}