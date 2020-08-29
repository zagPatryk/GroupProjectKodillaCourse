package com.kodilla.ecommercee.domain.order;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.order.dao.OrderDao;
import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;

//@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuit {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CartDao cartDao;

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
    public void testReadOrder() {
        //Given
        Order order1 = new Order();
        User user = new User();
        Order order2 = new Order(user, new Cart(user));
        //When
        orderDao.save(order1);
        orderDao.save(order2);
        long id1 = order1.getOrderId();
        long id2 = order2.getOrderId();
        Optional<Order> checkOrder1 = orderDao.findById(id1);
        Optional<Order> checkOrder2 = orderDao.findById(id2);
        User user1 = checkOrder1.get().getUser();
        Cart cart2 = checkOrder2.get().getCart();
        //Then
        assertEquals(checkOrder1, Optional.of(order1));
        assertEquals(checkOrder2, Optional.of(order2));
        assertEquals(null, user1);
        assertEquals(order2.getCart(), cart2);
        //Clean Up
        orderDao.deleteById(id1);
        orderDao.deleteById(id2);
    }

    @Test
    public void testUpdateOrder() {
        //Given
        Order order1 = new Order();
        Order order2 = new Order(new User(), new Cart());
        //When
        orderDao.save(order1);
        orderDao.save(order2);
        long id1 = order1.getOrderId();
        long id2 = order2.getOrderId();
        Optional<Order> checkOrder1 = orderDao.findById(id1);
        Optional<Order> checkOrder2 = orderDao.findById(id2);
        User user1 = checkOrder1.get().getUser();
        Cart cart2 = checkOrder2.get().getCart();
        //Then
        order1.setUser(new User());
        order2.setCart(new Cart());
        orderDao.save(order1);
        orderDao.save(order2);
        User updatedUser1 = checkOrder1.get().getUser();
        Cart updatedCart2 = checkOrder2.get().getCart();

        assertNotEquals(user1, updatedUser1);
        assertNotEquals(cart2, updatedCart2);
        //Clean Up
        orderDao.deleteById(id1);
        orderDao.deleteById(id2);
    }

    @Test
    public void testDeleteOrder() {
        //Given
        Order order = new Order();
        orderDao.save(order);
        long id = order.getOrderId();
        Optional<Order> checkOrder = orderDao.findById(id);
        //When
        orderDao.deleteById(id);
        Optional<Order> checkDeletedOrder = orderDao.findById(id);
        //Then
        assertFalse(checkDeletedOrder.isPresent());
    }

    @Test
    public void testDeleteOrderWithoutDeleteComponents() {
        //Given
        User user = new User();
        Cart cart = new Cart();
        Order order = new Order(user, cart);

        orderDao.save(order);
        cartDao.save(cart);
        long orderId = order.getOrderId();
        long cartId = cart.getId();
        //When
        Optional<Order> checkOrder = orderDao.findById(orderId);
        Optional<Cart> checkCart = cartDao.findById(cartId);

        assertTrue(checkOrder.isPresent());
        assertTrue(checkCart.isPresent());

        orderDao.deleteById(orderId);

        Optional<Order> checkDeletedOrder = orderDao.findById(orderId);
        Optional<Cart> checkUndeletedCart = cartDao.findById(cartId);

        //Then
        assertFalse(checkDeletedOrder.isPresent());
        assertTrue(checkUndeletedCart.isPresent());
        //Clean Up
        cartDao.deleteById(cartId);
    }
}