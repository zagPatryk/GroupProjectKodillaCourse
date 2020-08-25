package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.cart.CartDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
public class CartController {
    @RequestMapping(method = RequestMethod.GET, value = "getNewCart")
    public CartDto getNewCart(@RequestParam String user) {
        return new CartDto(new ArrayList<>());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<String> getProductsFromCart(@RequestParam String user) {
        return getNewCart("user").getCart();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addProductToCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto addProductToCart(@RequestBody String product) {
        CartDto cartDto = new CartDto(getProductsFromCart("user"));
        cartDto.getCart().add(product);
        return cartDto;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public String deleteProductFromCart(@RequestParam String product) {
        return "item deleted";
    }

    @RequestMapping(method = RequestMethod.POST, value = "addNewOrder", consumes = APPLICATION_JSON_VALUE)
    public String addNewOrder(@RequestBody CartDto cartDto) {
        return "New Order created";
    }
}
