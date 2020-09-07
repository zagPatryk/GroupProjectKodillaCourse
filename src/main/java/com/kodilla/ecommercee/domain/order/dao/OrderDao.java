package com.kodilla.ecommercee.domain.order.dao;

import com.kodilla.ecommercee.domain.order.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface OrderDao extends CrudRepository<Order, Long> {
    @Override
    List<Order> findAll();

    @Override
    Optional<Order> findById(Long id);

    @Override
    Order save(Order order);

    @Override
    void deleteById(Long id);
}
