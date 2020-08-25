package com.kodilla.ecommercee.domain.group;

import com.kodilla.ecommercee.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name="product_group")
public class Group {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "GROUP_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "groupId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Product> products = new ArrayList<>();
}
