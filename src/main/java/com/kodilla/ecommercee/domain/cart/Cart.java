package com.kodilla.ecommercee.domain.cart;

import com.kodilla.ecommercee.domain.order.Order;
import com.kodilla.ecommercee.domain.product.Product;

import com.kodilla.ecommercee.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "cart")
public class Cart {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @Column(name = "CART_ID")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "carts")
    private List<Product> productsList = new ArrayList<>();

    @OneToOne
    @NotNull
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;


    public void addProduct(Product product) {
        productsList.add(product);
        product.getCarts().add(this);
    }

    public Cart(User user) {
        this.user = user;
    }

    public Cart(User user, Product... products) {
        this.user = user;
        productsList.addAll(Arrays.asList(products));
    }

    public Cart(User user, List<Product> productsList) {
        this.user = user;
        this.productsList = productsList;
    }
}
