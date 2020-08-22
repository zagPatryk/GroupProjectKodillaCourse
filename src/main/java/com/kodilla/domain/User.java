package com.kodilla.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="User")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name="USER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="USER_STATUS")
    private boolean isActive = true;

    public User (String username, String password, String address){
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public long createUserKey() {
        long leftLimit = 100000L;
        long rightLimit = 999999L;
        long UserKey = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        return UserKey;
    }

    public boolean blockUser(){
        return isActive = false;
    }

    public static User createUser(String username, String password, String address){
        return new User(username, password, address);
    }
}
