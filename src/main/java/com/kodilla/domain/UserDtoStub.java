package com.kodilla.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDtoStub {

    private Long id;
    private String username;
    private String password;
    private String address;
    private boolean isActive;
}
