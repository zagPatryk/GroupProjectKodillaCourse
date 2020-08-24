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
@Table(name="GROUPS")
public class GroupEntity {

    @Id
    @GeneratedValue
    @Column(name = "GROUP_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "groupId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }
}
