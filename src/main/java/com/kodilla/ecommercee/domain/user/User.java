package com.kodilla.ecommercee.domain.user;

import com.kodilla.ecommercee.domain.order.Order;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class User {
    @OneToMany
    @JoinColumn(name = "ORDER_ID")
    private Order order;
}
