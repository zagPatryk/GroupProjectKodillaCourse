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
    public List<OrdersDto> getOrders() {
        return getTempOrderDtoList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getOrder")
    public OrdersDto getGroup(@RequestParam long orderId) {
        return getTempOrderDtoList().get((int) orderId);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "deleteOrder")
    public void deleteGroup(@RequestParam long orderId) {
        System.out.println("Order number: " + orderId + " has been deleted");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateOrder", consumes = APPLICATION_JSON_VALUE)
    public OrdersDto updateGroup(@RequestBody OrdersDto ordersDto) {
        return ordersDto;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createOrder", consumes = APPLICATION_JSON_VALUE)
    public OrdersDto createGroup(@RequestBody OrdersDto ordersDto) {
        return ordersDto;
    }

    public List<OrdersDto> getTempOrderDtoList() {
        return IntStream.range(0, 20)
                .mapToObj(order -> new OrdersDto((long) order, "Order name: " + order))
                .collect(Collectors.toList());
    }
}
