package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @RequestMapping(method = RequestMethod.GET, value = "getOrders")
    public List<OrderDto> getGroups() {
        return getOrderDtoList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrderDto getGroup(@RequestParam long orderId) {
        return getOrderDtoList().get((int) orderId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public boolean deleteGroup(@RequestParam Long orderId) {
        return true;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto updateGroup(@RequestBody OrderDto orderDto) {
        return orderDto;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto createGroup(@RequestBody OrderDto orderDto) {
        return orderDto;
    }

    public List<OrderDto> getOrderDtoList() {
        return IntStream.range(0, 20)
                .mapToObj(order -> new OrderDto((long) order, "Order name: " + order))
                .collect(Collectors.toList());
    }
}
