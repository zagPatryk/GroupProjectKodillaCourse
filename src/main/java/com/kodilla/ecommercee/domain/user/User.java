package com.kodilla.ecommercee.domain.user;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class User {
    // temporary
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private long id;
}
