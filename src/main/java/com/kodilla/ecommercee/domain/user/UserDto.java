package com.kodilla.ecommercee.domain.user;

import lombok.Getter;

@Getter
public class UserDto {

    private Long id;
    private Long userkey;
    private String username;
    private String password;
    private String address;
    private boolean isActive;


}
