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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Order(Cart cart) {
        addUser(cart.getUser());
        addProducts(cart.getProductsList());
    }

    public Order(User user, Cart cart) {
        addUser(user);
        addProducts(cart.getProductsList());
    }

    public void addUser(User user) {
        this.user = user;
        user.getOrder().add(this);
    }

    public void addProducts(List<Product> products) {
        for (Product product: products) {
            this.addProduct(product);
        }
    }

    public void addProduct(Product product) {
        this.orderList.add(product);
        product.getOrders().add(this);
    }

    public void resetProducts() {
        for (Product product: this.orderList) {
            product.getOrders().remove(this);
        }
        this.orderList.clear();
    }
}
