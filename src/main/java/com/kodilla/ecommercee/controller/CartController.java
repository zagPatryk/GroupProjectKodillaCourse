package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.CartDto;
import com.kodilla.ecommercee.domain.cart.CartDtoStub;
import com.kodilla.ecommercee.domain.order.OrderDto;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private CartService cartService;
    private CartMapper cartMapper;
    private ProductMapper productMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getNewCart")
    public Cart getNewCart(@RequestParam User user) {
        return cartService.getNewCart(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<Product> getProductsFromCart(@RequestParam CartDto cartDto) {
        return cartService.getAllProductsFromEmptyCart(cartDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addProductToCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto addProductToCart(@RequestBody CartDto cartDto, Product product) {
        return cartService.addProductsToCart(cartDto,product);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart")
    public boolean deleteProductFromCart(@RequestParam CartDto cartDto, Product product) {
        cartService.deleteSelectedProductFromCart(cartDto,product);
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "addNewOrder", consumes = APPLICATION_JSON_VALUE)
    public OrderDto addNewOrder(@RequestBody CartDto cartDto) {
        return cartService.createOrder(cartDto);
    }
}
