package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.CartDto;
import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.order.Order;
import com.kodilla.ecommercee.domain.order.OrderDto;
import com.kodilla.ecommercee.domain.order.dao.OrderDao;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.UserDto;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductDao productDao;

    public Cart getNewCart(User user) {
        Cart cart = new Cart(user);
        return cartDao.save(cart);
    }

    public List<Product> getAllProductsFromEmptyCart(CartDto cartDto) {

        List<Product> productList = cartDto.getProducts().stream()
                .map(e -> productDao.findById(e.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return productList;
    }

    public CartDto addProductsToCart(CartDto cartDto, Product product) {

        Cart cart = cartDao.findById(cartDto.getCartId()).get();

        cart.addProduct(product);

        return cartMapper.mapToCartDto(cartDao.save(cart));
    }

    public void deleteSelectedProductFromCart(CartDto cartDto, Product product) {

        Cart selectedCart = cartDao.findById(cartDto.getCartId()).get();

        List<Product> productsInCart = cartDto.getProducts().stream()
                .map(e -> productDao.findById(e.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        productsInCart.remove(product);

        cartMapper.mapToCartDto(cartDao.save(selectedCart));
    }

    public OrderDto createOrder(CartDto cartDto) {
        Optional<Cart> orderFromCart = cartDao.findById(cartDto.getCartId());
        if (orderFromCart.isPresent() && orderFromCart.get().getProductsList().size() > 0) {
            Order order = new Order(orderFromCart.get());
            return orderMapper.mapToOrderDto(orderDao.save(order));
        } else {
            return new OrderDto();
        }
    }
}