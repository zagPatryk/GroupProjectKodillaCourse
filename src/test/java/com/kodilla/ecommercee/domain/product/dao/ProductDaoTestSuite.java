package com.kodilla.ecommercee.domain.product.dao;

import com.kodilla.ecommercee.data.CartEntity;
import com.kodilla.ecommercee.domain.cart.dao.CartDaoStub;
import com.kodilla.ecommercee.domain.group.GroupEntity;
import com.kodilla.ecommercee.domain.group.dao.GroupEntityDao;
import com.kodilla.ecommercee.domain.product.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTestSuite {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CartDaoStub cartDaoStub;
    @Autowired
    private GroupEntityDao groupEntityDao;

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

    @Transactional
    @Test
    public void testCreateAndReadProductWithGroup() {
        // Given
        Product product = new Product("test", "testProduct", 100.0);
        GroupEntity group = new GroupEntity("kurtki");

        product.addGroup(group);

        // When
        groupEntityDao.save(group);
        productDao.save(product);

        Long productId = product.getId();
        Long groupId = group.getId();

        // Then
        Assert.assertEquals(product, productDao.findById(productId).orElse(null));
        Assert.assertEquals(product.getGroupId(), Objects.requireNonNull(productDao.findById(productId).orElse(null)).getGroupId());

        // Clean-up
        productDao.deleteById(productId);
        groupEntityDao.deleteById(groupId);
    }

    @Test
    public void testCreateAndReadProductWithCart() {
        // Given
        Product product = new Product("test", "testProduct", 100.0);
        CartEntity cart = new CartEntity();

        cart.addProduct(product);

        // When
        cartDaoStub.save(cart);
        productDao.save(product);

        Long productId = product.getId();
        Long cartId = cart.getId();

        // Then
        Assert.assertEquals(product, productDao.findById(productId).orElse(new Product()));
        Assert.assertTrue(product.getCarts().contains(cartDaoStub.findById(cartId).orElse(new CartEntity())));

        // Clean-up
        productDao.deleteById(productId);
        cartDaoStub.deleteById(cartId);
    }

    @Transactional
    @Test
    public void testUpdateProduct() {
        // Given
        GroupEntity group = new GroupEntity("group1");
        Product product = new Product("test", "testProduct", 100.0, group);
        CartEntity cart = new CartEntity();
        product.addToCart(cart);

        // When
        productDao.save(product);
        Long productId = product.getId();

        // Then
        Product retrievedProduct = productDao.findById(productId).orElse(new Product());
        Assert.assertEquals("test", retrievedProduct.getName());
        Assert.assertEquals("testProduct", retrievedProduct.getDescription());
        Assert.assertEquals(100.0, retrievedProduct.getPrice(), 0);
        Assert.assertEquals(group, retrievedProduct.getGroupId().get(0));
        Assert.assertEquals(cart, retrievedProduct.getCarts().get(0));

        // When (updated)
        String updatedName = "testUpdated";
        String updatedDesc = "testProductUpdated";
        double updatedPrice = 10.0;

        product.setName(updatedName);
        product.setDescription(updatedDesc);
        product.setPrice(updatedPrice);

        GroupEntity newGroup = new GroupEntity("group2");
        groupEntityDao.save(newGroup);

        CartEntity newCart = new CartEntity();
        cartDaoStub.save(newCart);

        product.addGroup(newGroup);
        product.addToCart(newCart);
        productDao.save(product);

        // Then (updated)
        retrievedProduct = productDao.findById(productId).orElse(new Product());
        Assert.assertEquals(updatedName, retrievedProduct.getName());
        Assert.assertEquals(updatedDesc, retrievedProduct.getDescription());
        Assert.assertEquals(updatedPrice, retrievedProduct.getPrice(), 0);
        Assert.assertEquals(2, retrievedProduct.getGroupId().size());
        Assert.assertTrue(retrievedProduct.getGroupId().contains(newGroup));
        Assert.assertEquals(2, retrievedProduct.getCarts().size());
        Assert.assertTrue(retrievedProduct.getCarts().contains(newCart));

        // Clean-up
        productDao.deleteById(productId);

        Long cartId = cart.getId();
        Long newCartId = newCart.getId();
        cartDaoStub.deleteById(cartId);
        cartDaoStub.deleteById(newCartId);

        Long groupId = group.getId();
        Long newGroupId = newGroup.getId();
        groupEntityDao.deleteById(groupId);
        groupEntityDao.deleteById(newGroupId);
    }

    private void assertsForTestUpdateProduct(Product product, String name, String desc, double price, int cartsCount, CartEntity presentCart, GroupEntity group) {
        Assert.assertEquals(name, product.getName());
        Assert.assertEquals(desc, product.getDescription());
        Assert.assertEquals(price, product.getPrice(), 0);
        Assert.assertEquals(cartsCount, product.getCarts().size());
        Assert.assertTrue(product.getCarts().contains(presentCart));
        Assert.assertEquals(group, product.getGroupId().get(0));
    }

    @Test
    public void testDeleteProduct() {
        // Given
        Product product = new Product("test", "testProduct", 100.0);
        GroupEntity group = new GroupEntity("kurtka");
        CartEntity cart = new CartEntity();

        product.addGroup(group);
        cart.addProduct(product);

        // When
        groupEntityDao.save(group);
        productDao.save(product);
        cartDaoStub.save(cart);

        Long groupId = group.getId();
        Long productId = product.getId();
        Long cartId = cart.getId();

        productDao.deleteById(productId);

        // Then
        Assert.assertTrue(groupEntityDao.findById(groupId).isPresent());
        Assert.assertEquals(0, groupEntityDao.findById(groupId).get().getProducts().size());

        Assert.assertTrue(cartDaoStub.findById(cartId).isPresent());
        Assert.assertEquals(0, cartDaoStub.findById(cartId).get().getProductsList().size());

    }
}
