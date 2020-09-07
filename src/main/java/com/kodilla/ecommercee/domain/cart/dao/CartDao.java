package com.kodilla.ecommercee.domain.cart.dao;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CartDao extends CrudRepository<Cart, Long> {
    Cart findByUser(User user);
}
