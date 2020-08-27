package com.kodilla.ecommercee.domain.user;

import com.kodilla.ecommercee.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    @Column(name = "user_id")
    private Long userId;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "orderId"
    )
    public List<Order> order = new ArrayList<>();

}
