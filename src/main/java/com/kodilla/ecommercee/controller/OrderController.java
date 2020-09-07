package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.cart.CartDto;
import com.kodilla.ecommercee.domain.cart.CartDtoStub;
import com.kodilla.ecommercee.domain.order.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@RequestParam long orderId) {
            return orderMapper.mapToOrderDto(orderService.getOrder(orderId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public boolean deleteOrder(@RequestParam long orderId) {
            return orderService.deleteOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
            return orderService.updateOrder(orderDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody CartDto cartDto) {
        return orderService.createOrder(cartDto);
    }
}
