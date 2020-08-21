package com.kodilla.ecommercee.domain.group;

import com.kodilla.ecommercee.domain.GenericEntity;
import com.kodilla.ecommercee.domain.product.Product;

import javax.persistence.Entity;
import java.util.List;

@Entity(name="Group")
public class Group extends GenericEntity {
    private List<Product> products;
}
