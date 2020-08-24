package com.kodilla.ecommercee.domain.product;

import com.kodilla.ecommercee.data.CartEntity;
import com.kodilla.ecommercee.domain.group.GroupEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name="Product")
public class Product {
    @Id
    @GeneratedValue
    @NotNull
    @EqualsAndHashCode.Include
    @Column(name="PRODUCT_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="PRICE")
    private double price;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "JOIN_PRODUCT_CART",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")}
    )
    private List<CartEntity> carts = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "JOIN_PRODUCT_GROUP",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID")}
    )
    private List<GroupEntity> groupId = new ArrayList<>();

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String name, String description, double price, List<GroupEntity> groups) {
        this(name, description, price);
        this.groupId.addAll(groups);
    }

    public Product(String name, String description, double price, GroupEntity group) {
        this(name, description, price);
        this.groupId.add(group);
    }

    public void addToCart(CartEntity cart) {
        this.carts.add(cart);
        cart.getProductsList().add(this);
    }

    public void removeFromCart(CartEntity cart) {
        this.carts.remove(cart);
        cart.getProductsList().remove(this);
    }

    public Product addGroup(GroupEntity group) {
        group.getProducts().add(this);
        this.groupId.add(group);
        return this;
    }

    public Product removeGroup(GroupEntity group) {
        group.getProducts().remove(this);
        this.groupId.remove(group);
        return this;
    }
}
