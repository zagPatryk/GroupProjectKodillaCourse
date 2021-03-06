package com.kodilla.ecommercee.domain.product;

import com.kodilla.ecommercee.domain.group.Group;
import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.order.Order;
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
    @EqualsAndHashCode.Include
    @NotNull
    @Column(name="PRODUCT_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="PRICE")
    private double price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "JOIN_PRODUCT_CART",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")}
    )
    private List<Cart> carts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "JOIN_PRODUCT_ORDER",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")}
    )
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID")
    private Group groupId;

    @Column(name="isActive")
    private boolean isActive = true;

    public void setActive(boolean status) {
        this.isActive = status;
        if (!status) {
            for (Cart cart: carts) {
                cart.getProductsList().remove(this);
            }
            this.carts = new ArrayList<>();
        }
    }

    public Product(Long id, String name, String description, double price, List<Cart> carts, Group groupId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.carts = carts;
        this.groupId = groupId;
    }

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(String name, String description, double price, Group groupId) {
        this(name, description, price);
        this.groupId = groupId;
    }

    public void addToCart(Cart cart) {
        this.carts.add(cart);
        cart.getProductsList().add(this);
    }

    public void addGroup(Group group) {
        group.getProducts().add(this);
        this.groupId = group;
    }
}
