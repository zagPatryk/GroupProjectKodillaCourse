package com.kodilla.ecommercee.domain.user;

import com.kodilla.ecommercee.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long userId;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "orderId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<Order> order;
}
