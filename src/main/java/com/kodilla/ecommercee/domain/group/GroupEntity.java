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

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "groupId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Product> products = new ArrayList<>();

    public GroupEntity(String name) {
        this.name = name;
    }
}
