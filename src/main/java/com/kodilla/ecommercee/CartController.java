package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/ecommerce/cart")
public class CartController {
    @RequestMapping(method = RequestMethod.GET, value = "createNewCart")
    public CartDto createNewCart() {
        return new CartDto(1L, new ArrayList<>());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductsFromCart")
    public List<String> getProductsFromCart(Long cartId) {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addProductToCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto addProductToCart(/*@RequestBody*/ Long productId) {
        return new CartDto(1L, Arrays.asList("product1"));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProductFromCart", consumes = APPLICATION_JSON_VALUE)
    public String deleteProductFromCart(/*@RequestParam*/ Long productId) {
        return "item deleted";
    }

    @RequestMapping(method = RequestMethod.POST, value = "createNewOrder", consumes = APPLICATION_JSON_VALUE)
    public String createNewOrder(/*@RequestBody*/ Long cartId) {
        return "New Order created";
    }
}
