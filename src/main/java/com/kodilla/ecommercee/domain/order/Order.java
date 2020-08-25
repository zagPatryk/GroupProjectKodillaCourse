package com.kodilla.ecommercee.domain.order;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    @NotNull
    @Column(name="order_id")
    private Long orderId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
