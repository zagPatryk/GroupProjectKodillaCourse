package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.CartDto;
import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.order.Order;
import com.kodilla.ecommercee.domain.order.OrderDto;
import com.kodilla.ecommercee.domain.order.dao.OrderDao;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import com.kodilla.ecommercee.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final UserDao userDao;
    private final OrderMapper orderMapper;
    private final CartDao cartDao;

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public Order getOrder(Long orderId) {
        return orderDao.findById(orderId)
                .orElseGet(Order::new);
    }

    @Transactional
    public OrderDto createOrder(CartDto cartDto) {
        Optional<Cart> cart = cartDao.findById(cartDto.getCartId());
        if (cart.isPresent() && cart.get().getProductsList().size() > 0) {
            Order order = new Order(cart.get());
            return orderMapper.mapToOrderDto(orderDao.save(order));
        } else {
            return new OrderDto();
        }
    }

    @Transactional
    public OrderDto updateOrder(OrderDto orderDto) {
        Optional<Order> orderDbRecord = orderDao.findById(orderDto.getOrderId());

        if (orderDbRecord.isPresent()) {
            Order orderForUpdate = orderDbRecord.get();

            List<Product> products = orderDto.getProducts().stream().
                    map(e -> productDao.findById(e.getId()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            orderForUpdate.resetProducts();
            orderForUpdate.addProducts(products);

            return orderMapper.mapToOrderDto(orderDao.save(orderForUpdate));
        } else {
            return new OrderDto();
        }
    }

    public boolean deleteOrder(Long orderId) {
        Optional<Order> dbRecord = orderDao.findById(orderId);
        if (dbRecord.isPresent()) {
            dbRecord.get().resetProducts();
            orderDao.save(dbRecord.get());
            orderDao.deleteById(orderId);
            return true;
        }
        return false;
    }
}
