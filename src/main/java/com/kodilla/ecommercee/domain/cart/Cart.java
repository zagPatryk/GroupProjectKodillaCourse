package com.kodilla.ecommercee.domain.cart;

import com.kodilla.ecommercee.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "carts")
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
