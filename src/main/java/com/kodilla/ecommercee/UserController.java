package com.kodilla.ecommercee;

import com.kodilla.domain.user.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public UserDto createUser(@RequestBody UserDto userDto){
        return userDto;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "blockUser")
    public void blockUser(@RequestParam long userId){
        System.out.println("User " + userId + " is blocked.");
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUserKey")
    public Long getUserKey(@RequestParam long userId){
        UserDto userDto = new UserDto();
        long userkey = userDto.getUserkey();
        System.out.println("UserKey for user " + userId + ": " + userkey);
        return userkey;
    }
}


