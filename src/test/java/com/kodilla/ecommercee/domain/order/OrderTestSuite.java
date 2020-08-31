package com.kodilla.ecommercee.domain.order;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.order.dao.OrderDao;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

//@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuite {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;

    @Test
    public void testCreateOrder() {
        //Given
        User user = new User();
        Order order = new Order(user, new Cart(user));
        //When
        orderDao.save(order);
        //Then
        long id = order.getOrderId();
        Optional<Order> checkOrder = orderDao.findById(id);
        assertTrue(checkOrder.isPresent());
        //Clean Up
        orderDao.deleteById(id);
    }

    @Test
    public void testReadOrder() {
        //Given
        User user = new User();
        Cart cart = new Cart(user);
        Order order = new Order(user, cart);
        //When
        orderDao.save(order);
        cartDao.save(cart);
        long orderId = order.getOrderId();
        long cartId = cart.getId();
        long userId = user.getId();
        Optional<Order> checkOrder = orderDao.findById(orderId);
        User checkUser = checkOrder.get().getUser();
        List<Product> productsList = checkOrder.get().getOrderList();
        //Then
        assertEquals(checkOrder, Optional.of(order));
        assertEquals(user, checkUser);
        assertEquals(order.getOrderList().size(), productsList.size());
        //Clean Up
        orderDao.deleteById(orderId);
        cartDao.deleteById(cartId);
        userDao.deleteById(userId);    }

    @Test
    public void testUpdateOrder() {
        //Given
        User user = new User();
        Cart cart = new Cart(user);
        Order order = new Order(user, cart);
        //When
        orderDao.save(order);
        cartDao.save(cart);
        long orderId = order.getOrderId();
        long cartId = cart.getId();
        long userId = user.getId();
        Optional<Order> checkOrder1 = orderDao.findById(orderId);
        User checkUser = checkOrder1.get().getUser();
        List<Product> checkList = checkOrder1.get().getOrderList();

        //Then
        order.setUser(new User());
        order.setOrderList(new Cart(new User()).getProductsList());
        orderDao.save(order);
        Optional<Order> updatedOrder = orderDao.findById(orderId);
        User updatedUser = updatedOrder.get().getUser();
        List<Product> updatedList = updatedOrder.get().getOrderList();

        assertNotEquals(checkUser, updatedUser);
        assertNotEquals(checkList, updatedList);
        //Clean Up
        orderDao.deleteById(orderId);
        cartDao.deleteById(cartId);
        userDao.deleteById(userId);
    }

    @Test
    public void testDeleteOrder() {
        //Given
        User user = new User();
        Cart cart = new Cart(user);
        Order order = new Order(user, cart);

        orderDao.save(order);
        long id = order.getOrderId();
        Optional<Order> checkOrder = orderDao.findById(id);
        assertTrue(checkOrder.isPresent());
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
        Product product = new Product("test", "test product", 10);
        Cart cart = new Cart(user, product);
        Order order = new Order(user, cart);

        orderDao.save(order);
        productDao.save(product);
        long userId = user.getId();
        long orderId = order.getOrderId();
        long productId = product.getId();
        //When
        Optional<Order> checkOrder = orderDao.findById(orderId);
        Optional<Product> checkProduct = productDao.findById(productId);

        assertTrue(checkOrder.isPresent());
        assertTrue(checkProduct.isPresent());

        orderDao.deleteById(orderId);

        Optional<Order> checkDeletedOrder = orderDao.findById(orderId);
        Optional<Product> checkUndeletedProduct = productDao.findById(productId);

        //Then
        assertFalse(checkDeletedOrder.isPresent());
        assertTrue(checkUndeletedProduct.isPresent());
        //Clean Up
        productDao.deleteById(productId);
        userDao.deleteById(userId);
    }
}