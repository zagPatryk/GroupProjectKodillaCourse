package com.kodilla.ecommercee.data;

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
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "carts")
    private List<Product> productsList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

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
