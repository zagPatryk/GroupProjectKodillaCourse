package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.order.Order;
import com.kodilla.ecommercee.domain.order.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getProducts(),
                orderDto.getUserId());
    }

    public Order mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getProductsList(),
                order.getUser().getId());
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orderList) {
        return orderList.stream()
                .map(o -> new OrderDto(
                        o.getOrderId(),
                        o.getProductsList(),
                        o.getUser()))
                .collect(Collectors.toList());
    }
}
