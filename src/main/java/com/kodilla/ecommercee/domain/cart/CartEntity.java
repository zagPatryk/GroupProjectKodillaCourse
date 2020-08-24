package com.kodilla.ecommercee.domain.cart;

import com.kodilla.ecommercee.domain.product.Product;

import com.kodilla.ecommercee.domain.user.User;
import lombok.AllArgsConstructor;
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
@Entity(name = "CART")
public class CartEntity {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "CART_ID")
    private Long id;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            mappedBy = "carts",
            fetch = FetchType.EAGER
    )
    private List<Product> productsList = new ArrayList<>();
  
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "USER_ID")
    private User user;

    public CartEntity addProduct(Product product) {
        productsList.add(product);
        product.getCarts().add(this);
        return this;
    }

    public CartEntity removeProduct(Product product) {
        productsList.remove(product);
        product.getCarts().remove(this);
        return this;
    }

    public CartEntity(User user) {
        this.user = user;
    }

    public CartEntity(User user, Product... products) {
        this.user = user;
        productsList.addAll(Arrays.asList(products));
    }

    public CartEntity(User user, List<Product> productsList) {
        this.user = user;
        this.productsList = productsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity that = (CartEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
