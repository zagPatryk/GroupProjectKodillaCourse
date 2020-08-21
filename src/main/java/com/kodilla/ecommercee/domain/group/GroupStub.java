package com.kodilla.ecommercee.domain.group;

import com.kodilla.ecommercee.GenericEntity;
import com.kodilla.ecommercee.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name="Group")
public class GroupStub {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "GROUP_ID")
    private Long id;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "groupId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Product> products;
}
