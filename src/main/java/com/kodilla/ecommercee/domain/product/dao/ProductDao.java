package com.kodilla.ecommercee.domain.product.dao;

import com.kodilla.ecommercee.domain.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ProductDao extends CrudRepository<Product, Long> {
}
