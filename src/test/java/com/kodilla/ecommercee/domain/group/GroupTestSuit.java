package com.kodilla.ecommercee.domain.group;

import com.kodilla.ecommercee.domain.group.dao.GroupDao;
import com.kodilla.ecommercee.domain.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;
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
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();
        Product product5 = new Product();
        Product product6 = new Product();
        Group group1 = new Group();
        Group group2 = new Group();

        List<Product> productList1 = Arrays.asList(product1, product2, product3);
        group1.setProducts(productList1);
        product1.setGroupId(group1);
        product2.setGroupId(group1);
        product3.setGroupId(group1);

        List<Product> productList2 = Arrays.asList(product4, product5, product6);
        group2.setProducts(productList2);
        product4.setGroupId(group2);
        product5.setGroupId(group2);
        product6.setGroupId(group2);

        // When
        groupDao.save(group1);
        long id1 = group1.getId();

        groupDao.save(group2);
        long id2 = group2.getId();

        // Then
        Optional<Group> groupIdDao1 = groupDao.findById(id1);
        assertTrue(groupIdDao1.isPresent());

        Optional<Group> groupIdDao2 = groupDao.findById(id2);
        assertTrue(groupIdDao2.isPresent());

        // Clear up
        groupDao.deleteById(id1);
        groupDao.deleteById(id2);
    }
}