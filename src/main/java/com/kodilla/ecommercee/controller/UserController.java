package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "blockUser")
    public User blockUser(@RequestParam long userId, @RequestParam int newUserStatus) {
        User user = userService.getUser(userId);
        user.setStatus(newUserStatus);
        userService.saveUser(user);
        return user;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public User getUser(@RequestParam long userId) {
        return userService.getUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUserKey")
    public Long getUserKey(@RequestParam Long userId) {
        User user = userService.getUser(userId);
        Long generatedUserKey = userService.createUserKey();
        user.setUserKey(generatedUserKey);
        userService.saveUser(user);
        return generatedUserKey;
    }

    @RequestMapping(method = RequestMethod.GET, value = "checkIfUserKeyIsValid")
    public boolean checkIfUserKeyIsValid(@RequestParam Long userId) {
        Long currentTimeSecs = System.currentTimeMillis()/1000;
        User user = userService.getUser(userId);
            if(currentTimeSecs - user.getUserKey() < 30){   //UserKey is active for 30 seconds for Postman tests purpose - for one hour change 30 -> 3600
                return true;
            } else {
                return false;
            }
    }
}













