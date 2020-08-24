package com.kodilla.ecommercee.domain.group;

import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.domain.product.Product;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="PRODUCT_GROUPS")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GroupEntity {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    @Column(name = "GROUP_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            mappedBy = "groupId",
            fetch = FetchType.EAGER
    )
    private List<Product> products = new ArrayList<>();

    public GroupEntity(String name) {
        this.name = name;
    }

    public GroupEntity addProduct(Product product) {
        this.products.add(product);
        product.addGroup(this);
        return this;
    }

    public GroupEntity removeProduct(Product product) {
        this.products.remove(product);
        product.removeGroup(this);
        return this;
    }
}
