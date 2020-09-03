package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.group.Group;
import com.kodilla.ecommercee.domain.group.dao.GroupDao;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTestSuite {

    // Convenience class setting up some data in the DB to test OrderController with POSTMAN

    @Autowired
    private GroupDao groupDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CartDao cartDao;

    @Test
    public void dataSetUp() {
//        // Given
//        Group g1 = new Group("kurtki");
//        Group g2 = new Group("buty");
//
//        Product p1 = new Product("Kurtka skorzana", "Kurtka skorzana marki Apacz", 150.0, g1);
//        Product p2 = new Product("Kurtka jeansowa", "Kurtka jeansowa marki Cotton", 100.0, g1);
//        Product p3 = new Product("Adidasy", "Buty sportowe Adidasy", 250.0, g2);
//        Product p4 = new Product("Najki", "Buty sportowe Najki", 200.0, g2);
//
//        User user = new User("Matt", 123, 123);
//        Cart cart = new Cart(user);
//        cart.addProduct(p1);
//        cart.addProduct(p2);
//        cart.addProduct(p3);
//        cart.addProduct(p4);
//
//        groupDao.saveAll(Arrays.asList(g1, g2));
//        userDao.save(user);
//        cartDao.save(cart);
//        productDao.saveAll(Arrays.asList(p1, p2, p3, p4));
//
//
//        // When
//
//
//        // Then

    }
}
