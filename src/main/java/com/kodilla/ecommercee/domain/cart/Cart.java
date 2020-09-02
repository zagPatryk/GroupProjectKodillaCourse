package com.kodilla.ecommercee.domain.cart;

import com.kodilla.ecommercee.domain.product.Product;

import com.kodilla.ecommercee.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @ManyToMany(mappedBy = "carts", fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    private List<Product> productsList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

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
        for (Product product : products) {
            product.getCarts().add(this);
        }
    }

    public Cart(User user, List<Product> productsList) {
        this.user = user;
        this.productsList = productsList;
        for (Product product : productsList) {
            product.getCarts().add(this);
        }
    }
}
