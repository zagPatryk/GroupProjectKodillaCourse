package com.kodilla.ecommercee.domain.order;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.order.dao.OrderDao;
import com.kodilla.ecommercee.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuit {
    @Autowired
    private OrderDao orderDao;

    @Test
    public void testCreateOrder() {
        //Given
        Order order = new Order();
        //When
        orderDao.save(order);
        //Then
        long id = order.getOrderId();
        Optional<Order> checkOrder = orderDao.findById(id);
        System.out.println(id);
        assertTrue(checkOrder.isPresent());
        //Clean Up
        orderDao.deleteById(id);
    }

    @Test
    public void testUpdateOrder() {
        
    }
}