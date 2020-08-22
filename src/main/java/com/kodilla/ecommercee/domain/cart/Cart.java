package com.kodilla.ecommercee.domain.cart;

import com.kodilla.ecommercee.domain.order.Order;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Cart {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "CART_ID")
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    private Order order;
}
