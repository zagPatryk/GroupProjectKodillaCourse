package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUser(final Long id) {
        if (userDao.findById(id).isPresent()) {
            return userDao.findById(id).get();
        } else {
            return new User();
        }
    }

    public User saveUser(final User user) {
        return userDao.save(user);
    }

    public User blockUser(long userId) {
        if (userDao.findById(userId).isPresent()) {
            User user = userDao.findById(userId).get();
            user.setStatus(0);
            userDao.save(user);
            return user;
        } else {
            return new User();
        }
    }

    public long createUserKey(long userId) {
        if (userDao.findById(userId).isPresent()) {
            User user = getUser(userId);
            long generatedUserKey = System.currentTimeMillis() / 1000;
            user.setUserKey(generatedUserKey);
            saveUser(user);
            return generatedUserKey;
        } else {
            return 0L;
        }
    }

    public boolean checkIfUserKeyIsValid(long userId) {
        User user = getUser(userId);
        return (System.currentTimeMillis() / 1000) - user.getUserKey() < 30;
    }
}