package com.kodilla.ecommercee.domain.group;

import com.kodilla.ecommercee.data.CartEntity;
import com.kodilla.ecommercee.domain.group.dao.GroupDao;
import com.kodilla.ecommercee.domain.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTestSuit {
    @Autowired
    private GroupDao groupDao;

    @Test
    public void testSaveGroup() {
        // Given
        CartEntity cartEntity = new CartEntity(1L, new ArrayList<Product>());
        Product product = new Product(1L, "Product name", "Desc", 10, new ArrayList<CartEntity>(), new Group());
        Group group = new Group(1L, "Name Group", new ArrayList<Product>());

        group.setProducts(new ArrayList<Product>());
        group.getProducts().add(product);
        product.getCarts().add(cartEntity);
        cartEntity.getProductsList().add(product);

        // When
        groupDao.save(group);
        long id = group.getId();

        // Then
        Optional<Group> groupIdDao = groupDao.findById(id);
        assertTrue(groupIdDao.isPresent());

        // Clear up
        groupDao.deleteById(id);
    }
}