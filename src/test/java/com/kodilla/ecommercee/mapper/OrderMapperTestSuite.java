package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.group.Group;
import com.kodilla.ecommercee.domain.group.dao.GroupDao;
import com.kodilla.ecommercee.domain.order.Order;
import com.kodilla.ecommercee.domain.order.OrderDto;
import com.kodilla.ecommercee.domain.order.dao.OrderDao;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.product.ProductDto;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTestSuite {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CartDao cartDao;

    @Test
    public void testMapToOrderDto() {
        // Given
        Group group = new Group("group");
        Product p1 = new Product("p1_test", "p1_desc", 10.0, group);
        Product p2 = new Product("p2_test", "p2_desc", 10.0, group);
        Product p3 = new Product("p3_test", "p3_desc", 10.0, group);
        Product p4 = new Product("p4_test", "p4_desc", 10.0, group);
        List<Product> products = Arrays.asList(p1, p2, p3, p4);

        groupDao.save(group);
        productDao.saveAll(products);

        User user = new User("Test_user", 1, 123);
        Cart cart = new Cart(user, products);
        user.setCart(cart);

        userDao.save(user);

        Order order = new Order(user, cart);

        orderDao.save(order);
        productDao.saveAll(products);

        // When
        Order orderReadBack = orderDao.findById(order.getOrderId()).orElse(new Order());

        OrderDto orderDto = orderMapper.mapToOrderDto(orderReadBack);

        // Then
        Assert.assertEquals(order.getOrderId(), orderDto.getOrderId());
        Assert.assertEquals(
                products.stream().map(Product::getId).collect(Collectors.toList()),
                orderDto.getProducts().stream()
                        .map(ProductDto::getId)
                        .collect(Collectors.toList())
        );
        Assert.assertEquals(order.getUser().getId(), orderDto.getUserId());

        // Clean-up
        for (Product product: products) {
            productDao.deleteById(product.getId());
        }
        groupDao.deleteById(group.getId());
        orderDao.deleteById(order.getOrderId());
        cartDao.deleteById(user.getCart().getId());
        userDao.deleteById(user.getId());
    }
}
