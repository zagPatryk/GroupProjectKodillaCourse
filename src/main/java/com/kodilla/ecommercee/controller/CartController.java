package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.cart.CartDto;
import com.kodilla.ecommercee.domain.order.OrderDto;
import com.kodilla.ecommercee.domain.product.ProductDto;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET, value = "getNewCart")
    public CartDto getNewCart(@RequestParam Long userId) {
        return cartService.getNewCart(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<ProductDto> getAllProductsFromCart(@RequestParam Long cartId) {
        return cartService.getAllProductsFromCart(cartId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addProductToCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto addProductToCart(@RequestParam Long cartId, @RequestParam Long productId) {
        return cartService.addProductToCart(cartId,productId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public void deleteProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) {
        cartService.deleteSelectedProductFromCart(cartId,productId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addNewOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto addNewOrder(@RequestBody CartDto cartDto) {
        return cartService.createOrder(cartDto);
    }
}