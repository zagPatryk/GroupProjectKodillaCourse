package com.kodilla.ecommercee.data;

import com.kodilla.ecommercee.domain.group.GroupEntity;
import com.kodilla.ecommercee.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cart")
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
