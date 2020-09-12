package com.kodilla.ecommercee.controller;

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
    public UserDto blockUser(@RequestParam long userId) {
        return userMapper.mapToUserDto(userService.blockUser(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam long userId) {
        return userMapper.mapToUserDto(userService.getUser(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUserKey")
    public UserDto getUserKey(@RequestParam Long userId) {
        return userMapper.mapToUserDto(userService.createUserKey(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "checkIfUserKeyIsValid")
    public boolean checkIfUserKeyIsValid(@RequestParam Long userId) {
        return userService.checkIfUserKeyIsValid(userId);
    }
}