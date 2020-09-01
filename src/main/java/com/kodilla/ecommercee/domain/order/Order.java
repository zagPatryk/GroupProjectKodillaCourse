package com.kodilla.ecommercee.domain.order;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    @NotNull
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},
            mappedBy = "orders" ,
            fetch = FetchType.EAGER)
    private List<Product> orderList = new ArrayList<>();

    @NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Order(User user, Cart cart) {
        this.user = user;
        this.orderList = cart.getProductsList();
        for (Product product : cart.getProductsList()) {
            product.getOrders().add(this);
        }
    }
}
