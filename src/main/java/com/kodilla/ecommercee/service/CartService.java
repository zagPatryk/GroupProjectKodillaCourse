package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.CartDto;
import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.order.OrderDto;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.product.ProductDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    @Autowired
    private final CartDao cartDao;
    @Autowired
    private final CartMapper cartMapper;
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final ProductMapper productMapper;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final UserService userService;

    public CartDto getNewCart(Long userId) {
        Cart cart = new Cart(userService.getUser(userId));
        cartDao.save(cart);
        return cartMapper.mapToCartDto(cart);
    }

    public List<ProductDto> getAllProductsFromCart(Long cartId) {
        Cart cart = cartDao.findById(cartId).orElseThrow(
                () -> new IllegalArgumentException("Cart not found")
        );
        return productMapper.mapToProductDto(cart.getProductsList());
    }

    public CartDto addProductToCart(Long cartId, Long productId) {
        Cart cart = cartDao.findById(cartId).orElse(new Cart());
        cart.addProduct(productService.getProduct(productId));
        return cartMapper.mapToCartDto(cartDao.save(cart));
    }

    public boolean deleteSelectedProductFromCart(Long cartId, Long productId) {
            Cart cart = cartDao.findById(cartId).orElseThrow(
                () -> new IllegalArgumentException("Cart not found")
            );
            Product productToRemove =productService.getProduct(productId);
            getAllProductsFromCart(cartId).remove(productToRemove);
            cartMapper.mapToCartDto(cartDao.save(cart));
    }

    public OrderDto createOrder(CartDto cartDto) {
        return orderService.createOrder(cartDto);
    }
}