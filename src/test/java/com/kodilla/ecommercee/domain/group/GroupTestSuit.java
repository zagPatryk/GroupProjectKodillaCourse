package com.kodilla.ecommercee.domain.group;

import com.kodilla.ecommercee.domain.group.dao.GroupDao;
import com.kodilla.ecommercee.domain.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
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
        Group group = new Group(1L, "Name Group", new ArrayList<Product>());
        // When
        groupDao.save(group);
        // Then
        long id = group.getId();
        Optional<Group> groupIdDao = groupDao.findById(id);
        assertTrue(groupIdDao.isPresent());
        // Clear up
        groupDao.deleteById(id);
    }
}