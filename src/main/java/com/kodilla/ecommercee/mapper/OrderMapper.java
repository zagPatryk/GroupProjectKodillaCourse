package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.order.Order;
import com.kodilla.ecommercee.domain.order.OrderDto;
import com.kodilla.ecommercee.domain.order.dao.OrderDao;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrderMapper {
    private final UserDao userDao;
    private final ProductDao productDao;
    private final OrderDao orderDao;
    private final ProductMapper productMapper;

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                productMapper.mapToProductDto(order.getOrderList()),
                Optional.ofNullable(order.getUser()).orElse(new User()).getId()
        );
    }

    public Order mapToOrder(OrderDto orderDto) {
        return orderDao.findById(orderDto.getOrderId()).orElseGet(
                () -> new Order(
                    orderDto.getOrderId(),
                    orderDto.getProducts().stream()
                            .map(e -> productDao.findById(e.getId())
                                    .orElseGet(null))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()),
                    userDao.findById(orderDto.getUserId())
                            .orElseGet(User::new))
        );
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }
}
