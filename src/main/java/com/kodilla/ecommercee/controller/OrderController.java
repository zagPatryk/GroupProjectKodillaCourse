package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.order.OrderDto;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;


    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getAllOrders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getOrder(@RequestParam long orderId) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.getOrder(orderId).orElseThrow(OrderNotFoundException::new));
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteOrder(@RequestParam long orderId) {
        orderService.deleteOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderService.saveOrder(orderMapper.mapToOrder(orderDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderMapper.mapToOrder(orderDto));
    }
}