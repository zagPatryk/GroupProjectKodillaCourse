package com.kodilla.ecommercee.domain.cart.dao;

import com.kodilla.ecommercee.data.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CartDaoStub extends CrudRepository<CartEntity, Long> {
}
