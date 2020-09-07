package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.order.Order;
import com.kodilla.ecommercee.domain.order.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public Optional<Order> getOrder(final Long orderId) {
        return orderDao.findById(orderId);
    }

    public Order saveOrder(Order order) {
        return orderDao.save(order);
    }

    public Order updateOrder(Order order) {
        return orderDao.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderDao.deleteById(orderId);
    }
}
