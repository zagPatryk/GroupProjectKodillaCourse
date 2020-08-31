package com.kodilla.ecommercee.domain.cart;

import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTestSuite {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;

    @Test
    public void testCreateCart() {
        //Given
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();

        List<Product> productList = new ArrayList<>();
        productList.add(product4);

        Cart cart1 = new Cart(user1, product1);
        Cart cart2 = new Cart(user2, product2, product3);
        Cart cart3 = new Cart(user3, productList);

        //When
        userDao.save(user1);
        userDao.save(user2);
        userDao.save(user3);

        cartDao.save(cart1);
        cartDao.save(cart2);
        cartDao.save(cart3);

        productDao.save(product1);
        productDao.save(product2);
        productDao.save(product3);
        productDao.save(product4);

        //Then
        Assert.assertTrue(cartDao.findById(cart1.getId()).isPresent());
        Assert.assertTrue(cartDao.findById(cart2.getId()).isPresent());
        Assert.assertTrue(cartDao.findById(cart3.getId()).isPresent());

        //CleanUp
        productDao.deleteById(product1.getId());
        productDao.deleteById(product2.getId());
        productDao.deleteById(product3.getId());
        productDao.deleteById(product4.getId());

        cartDao.deleteById(cart1.getId());
        cartDao.deleteById(cart2.getId());
        cartDao.deleteById(cart3.getId());

        userDao.deleteById(user1.getId());
        userDao.deleteById(user2.getId());
        userDao.deleteById(user3.getId());
    }

    @Test
    public void testReadCart() {
        //Given
        User user = new User();
        Product product = new Product();
        Cart cart = new Cart(user, product);

        //When
        userDao.save(user);
        cartDao.save(cart);
        Cart cartFromDb = cartDao.findById(cart.getId()).get();

        //Then
        Assert.assertEquals(cart, cartFromDb);

        //CleanUp
        cartDao.deleteById(cart.getId());
        userDao.deleteById(user.getId());
    }

    @Test
    public void testUpdateCart() {
        // Given
        User user = new User();
        Product product1 = new Product();
        Product product2 = new Product();
        Cart cart = new Cart(user, product1);

        userDao.save(user);
        cartDao.save(cart);
        productDao.save(product1);
        productDao.save(product2);

        // When
        Cart cartToUpdate = cartDao.findById(cart.getId()).get();
        cartToUpdate.addProduct(product2);

        cartDao.save(cartToUpdate);

        // Then
        Assert.assertEquals(cartDao.findById(cart.getId()).get().getProductsList().size(),
                cartToUpdate.getProductsList().size());
        Assert.assertEquals(cartDao.findById(cart.getId()).get().getId(), cart.getId());
        Assert.assertTrue(cartDao.findById(cart.getId()).get().getProductsList().contains(product2));

        // Clean-up
        productDao.deleteById(product1.getId());
        productDao.deleteById(product2.getId());
        cartDao.deleteById(cart.getId());
        userDao.deleteById(user.getId());
    }

    @Test
    public void testDeleteCart() {
        //Given
        User user = new User();
        Cart cart = new Cart(user);

        //When
        userDao.save(user);
        cartDao.save(cart);
        cartDao.deleteById(cart.getId());

        //Then
        Assert.assertFalse(cartDao.findById(cart.getId()).isPresent());

        //CleanUp
        userDao.deleteById(user.getId());
    }
}
