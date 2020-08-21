package com.kodilla.ecommercee.domain.product;

import com.kodilla.ecommercee.data.CartEntity;
import com.kodilla.ecommercee.domain.GenericEntity;
import com.kodilla.ecommercee.domain.group.GroupStub;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@Entity(name="Product")
public class Product {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="PRICE")
    private BigDecimal price;

    @ManyToMany
    @JoinTable(
            name = "JOIN_PRODUCT_CART",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "id")}
    )
    private List<CartEntity> carts;
}
