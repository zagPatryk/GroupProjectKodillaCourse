package com.kodilla.ecommercee.domain.user.dao;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.order.Order;
import com.kodilla.ecommercee.domain.order.dao.OrderDao;
import com.kodilla.ecommercee.domain.user.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTestSuite {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private OrderDao orderDao;

    @Test
    public void testCreateAndReadUser() {
        //Given
        User user = new User();
        Cart cart = new Cart(user);
        Order order = new Order(user,cart);

        //When
        userDao.save(user);
        cartDao.save(cart);
        orderDao.save(order);

        User readUser = userDao.findById(user.getId()).orElse(user);

        //Then
        Assert.assertTrue(userDao.findById(user.getId()).isPresent());
        Assert.assertEquals(user,readUser);

        //CleanUp
        orderDao.deleteById(order.getOrderId());
        cartDao.deleteById(cart.getId());
        userDao.deleteById(user.getId());
    }

    @Test
    public void testUpdateUser() {
        //Given
        User user = new User("Test_Username", 0,123456);
        Cart cart = new Cart(user);
        Order order = new Order(user,cart);
        Order order1 = new Order(user,cart);

        //When
        userDao.save(user);
        cartDao.save(cart);
        orderDao.save(order);

        User userBeforeUpdate = userDao.findById(user.getId()).orElse(user);

        //Then
        Assert.assertEquals(user, userBeforeUpdate);
        Assert.assertEquals("Test_Username", userBeforeUpdate.getUsername());
        Assert.assertEquals(0, userBeforeUpdate.getStatus());
        Assert.assertEquals(123456, userBeforeUpdate.getUserKey());

        // Update
        user.setUsername("testUpdateUsername");
        user.setStatus(1);
        user.setUserKey(654321);

        // When
        userDao.save(user);
        User userAfterUpdate = userDao.findById(user.getId()).orElse(user);

        // Then
        Assert.assertEquals(user, userAfterUpdate);
        Assert.assertEquals("testUpdateUsername", userAfterUpdate.getUsername());
        Assert.assertEquals(1, userAfterUpdate.getStatus());
        Assert.assertEquals(654321, userAfterUpdate.getUserKey());

        //CleanUp
        orderDao.deleteById(order.getOrderId());
        cartDao.deleteById(cart.getId());
        userDao.deleteById(user.getId());
    }

    @Test
    public void testDeleteUser() {
        // Given
        User user = new User();
        Cart cart = new Cart(user);
        Order order = new Order(user,cart);

        userDao.save(user);
        orderDao.save(order);

        long userId = user.getId();
        Optional<User> checkUser = userDao.findById(userId);
        Assert.assertTrue(checkUser.isPresent());

        //When
        orderDao.deleteById(order.getOrderId());

        userDao.deleteById(userId);
        Optional<User> checkIfUserIsDeleted = userDao.findById(userId);

        //Then
        Assert.assertFalse(checkIfUserIsDeleted.isPresent());
    }
}
