package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.order.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getOrders() {
        return getTempOrderDtoList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getGroup(@RequestParam long orderId) {
        return getTempOrderDtoList().get((int) orderId);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteGroup(@RequestParam long orderId) {
        System.out.println("Order number: " + orderId + " has been deleted");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto updateGroup(@RequestBody OrderDto orderDto) {
        return orderDto;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto createGroup(@RequestBody OrderDto orderDto) {
        return orderDto;
    }

    public List<OrderDto> getTempOrderDtoList() {
        return IntStream.range(0, 20)
                .mapToObj(order -> new OrderDto())
                .collect(Collectors.toList());
    }
}
