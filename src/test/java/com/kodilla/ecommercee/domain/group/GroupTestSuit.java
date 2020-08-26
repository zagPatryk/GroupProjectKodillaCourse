package com.kodilla.ecommercee.domain.group;

import com.kodilla.ecommercee.domain.group.dao.GroupDao;
import com.kodilla.ecommercee.domain.product.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTestSuit {
    @Autowired
    private GroupDao groupDao;

    @Test
    public void testCreateGroup() {
        // Given
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        Product product4 = new Product();

        Group group1 = new Group();
        Group group2 = new Group();

        group1.getProducts().add(product1);
        group1.getProducts().add(product2);
        product1.setGroupId(group1);
        product2.setGroupId(group1);

        group2.getProducts().add(product3);
        group2.getProducts().add(product4);
        product3.setGroupId(group2);
        product4.setGroupId(group2);

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

    @Test
    public void testReadGroup() {
        // Given
        Product product1 = new Product();
        Product product2 = new Product();
        Group group = new Group();

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        product1.setGroupId(group);
        product2.setGroupId(group);

        // When
        groupDao.save(group);
        long id = group.getId();

        // Then
        Group actual = groupDao.findById(id).orElse(new Group());
        Assert.assertEquals(group, actual);

        // Clean-up
        groupDao.deleteById(id);
    }

    @Test
    public void testDeleteGroup() {
        // Given
        Product product1 = new Product();
        Product product2 = new Product();
        Group group = new Group();

        group.getProducts().add(product1);
        group.getProducts().add(product2);
        product1.setGroupId(group);
        product2.setGroupId(group);

        // When
        groupDao.save(group);
        long id = group.getId();
        groupDao.deleteById(id);

        // Then
        Assert.assertFalse(groupDao.findById(id).isPresent());
    }

    @Test
    public void testUpdateGroup() {
        // Given
        Group group = new Group("text");

        // When
        groupDao.save(group);
        Long id1 = group.getId();
        Group groupDaoId1 = groupDao.findById(id1).orElse(group);

        // Then
        Assert.assertEquals(group.getId(), groupDaoId1.getId(), 0);
        Assert.assertEquals("text", groupDaoId1.getName());

        // Update
        group.setName("newText");

        // When
        groupDao.save(group);
        Group groupDaoId2 = groupDao.findById(id1).orElse(group);

        // Then
        Assert.assertEquals(group.getId(), groupDaoId2.getId(), 0);
        Assert.assertEquals("newText", groupDaoId2.getName());

        // Clean-up
        groupDao.deleteById(id1);
    }
}