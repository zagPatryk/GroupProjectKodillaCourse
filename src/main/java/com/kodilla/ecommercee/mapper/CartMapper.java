package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.CartDto;
import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.dao.UserDao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartMapper {
    private UserDao userDao;
    private ProductDao productDao;
    private ProductMapper productMapper;
    private CartDao cartDao;

    public Cart mapToCart(CartDto cartDto) {
        return cartDao.findById(cartDto.getCartId()).orElseGet(
                () -> new Cart(
                        cartDto.getCartId(),
                        cartDto.getProducts().stream()
                                .map(e -> productDao.findById(e.getId())
                                        .orElseGet(null))
                                .filter(Objects::nonNull)
                                .collect(Collectors.toList()),
                        userDao.findById(cartDto.getUserId())
                                .orElseGet(User::new))
        );
    }

    public CartDto mapToCartDto(Cart cart) {
        return new CartDto(
                cart.getId(),
                productMapper.mapToProductDto(cart.getProductsList()),
                Optional.ofNullable(cart.getUser()).orElse(new User()).getId()
        );
    }

    public List<CartDto> mapToListCartDto(final List<Cart> cartList) {
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
}