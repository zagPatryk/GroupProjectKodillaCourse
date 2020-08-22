package com.kodilla.ecommercee;

import com.kodilla.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/user")
public class UserController {

    private User user;

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public User createUser(@RequestParam String username, @RequestParam String password, @RequestParam String address){
        return User.createUser(username, password, address);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "blockUser")
    public boolean blockUser(){
        return user.blockUser();

    }
    @RequestMapping(method = RequestMethod.GET, value = "getUserKey")
    public Long getUserKey(){
        return user.createUserKey();
    }
}


