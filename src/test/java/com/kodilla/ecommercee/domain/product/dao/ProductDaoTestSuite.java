package com.kodilla.ecommercee.domain.product.dao;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.group.Group;
import com.kodilla.ecommercee.domain.group.dao.GroupDao;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Objects;

//@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTestSuite {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void testCreateAndReadProduct() {
        // Given
        Product product = new Product("test", "testProduct", 100.0);

        // When
        productDao.save(product);
        Long productId = product.getId();

        // Then
        Assert.assertEquals(product, productDao.findById(productId).orElse(null));

        // Clean-up
        productDao.deleteById(productId);
    }

    @Test
    public void testCreateAndReadProductWithGroup() {
        // Given
        Product product = new Product("test", "testProduct", 100.0);
        Group group = new Group("kurtki");

        product.addGroup(group);

        // When
        groupDao.save(group);
        productDao.save(product);

        Long productId = product.getId();
        Long groupId = group.getId();

        // Then
        Assert.assertEquals(product, productDao.findById(productId).orElse(null));
        Assert.assertEquals(product.getGroupId(), Objects.requireNonNull(productDao.findById(productId).orElse(null)).getGroupId());

        // Clean-up
        productDao.deleteById(productId);
        groupDao.deleteById(groupId);
    }

    @Test
    public void testCreateAndReadProductWithCart() {
        // Given
        Product product = new Product("test", "testProduct", 100.0);
        User user = new User();
        Cart cart = new Cart(user);

        cart.addProduct(product);

        // When
        userDao.save(user);
        cartDao.save(cart);
        productDao.save(product);

        Long productId = product.getId();
        Long cartId = cart.getId();

        // Then
        Assert.assertEquals(product, productDao.findById(productId).orElse(new Product()));
        Assert.assertTrue(product.getCarts().contains(cartDao.findById(cartId).orElse(new Cart())));

        // Clean-up
        productDao.deleteById(productId);
        cartDao.deleteById(cartId);
    }

    @Test
    public void testUpdateProduct() {
        // Given
        String originalName = "test";
        String originalDesc = "testProduct";
        double originalPrice = 100.0;
        Product product = new Product(originalName, originalDesc, originalPrice);

        Group group = new Group("kurtka");
        Group newGroup = new Group("sweter");

        User user = new User();

        Cart cart = new Cart(user);
        Cart newCart = new Cart(user);

        product.addGroup(group);
        cart.addProduct(product);

        // When
        userDao.save(user);
        cartDao.saveAll(Arrays.asList(cart, newCart));
        groupDao.saveAll(Arrays.asList(group, newGroup));
        productDao.save(product);

        Long productId = product.getId();

        Product productDbEntry = productDao.findById(productId).orElse(new Product());

        // Then
        assertsForTestUpdateProduct(productDbEntry, originalName, originalDesc, originalPrice, 1, cart, group);

        // When (updated)
        String updatedName = "testUpdated";
        String updatedDesc = "testProductUpdated";
        double updatedPrice = 10.0;

        productDbEntry.setName(updatedName);
        productDbEntry.setDescription(updatedDesc);
        productDbEntry.setPrice(updatedPrice);

        productDbEntry.addGroup(newGroup);
        newCart.addProduct(productDbEntry);

//        cartDao.save(cart);
        productDao.save(productDbEntry);

        productDbEntry = productDao.findById(productId).orElse(new Product());

        // Then (updated)
        assertsForTestUpdateProduct(productDbEntry, updatedName, updatedDesc, updatedPrice, 2, newCart, newGroup);

        // Clean-up
        productDao.deleteById(productId);

        Long cartId = cart.getId();
        cartDao.deleteById(cartId);

        Long groupId = group.getId();
        Long newGroupId = newGroup.getId();
        groupDao.deleteById(groupId);
        groupDao.deleteById(newGroupId);
    }

    private void assertsForTestUpdateProduct(Product product, String name, String desc, double price, int cartsCount, Cart presentCart, Group group) {
        Assert.assertEquals(name, product.getName());
        Assert.assertEquals(desc, product.getDescription());
        Assert.assertEquals(price, product.getPrice(), 0);
        Assert.assertEquals(cartsCount, product.getCarts().size());
        Assert.assertTrue(product.getCarts().contains(presentCart));
        Assert.assertEquals(group, product.getGroupId());
    }

    @Test
    public void testDeleteProduct() {
        // Given
        Product product = new Product("test", "testProduct", 100.0);
        Group group = new Group("kurtka");
        User user = new User();
        Cart cart = new Cart(user);

        product.addGroup(group);
        cart.addProduct(product);

        // When
        userDao.save(user);
        groupDao.save(group);
        cartDao.save(cart);
        productDao.save(product);

        Long groupId = group.getId();
        Long productId = product.getId();
        Long cartId = cart.getId();

        productDao.deleteById(productId);

        // Then
        Assert.assertTrue(cartDao.findById(cartId).isPresent());
        Assert.assertEquals(0, cartDao.findById(cartId).get().getProductsList().size());

        Assert.assertTrue(groupDao.findById(groupId).isPresent());
        Assert.assertEquals(0, groupDao.findById(groupId).get().getProducts().size());
    }
}
