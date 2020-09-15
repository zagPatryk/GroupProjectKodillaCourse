package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.CartDto;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductMapper productMapper;

    public Cart mapToCart(CartDto cartDto) {
        if (cartDto.getCartId() == null) {
            return new Cart();
        } else {
            return new Cart(
                    cartDto.getCartId(),
                    cartDto.getProducts().stream()
                            .map(e -> productDao.findById(e.getId())
                                    .orElseGet(null))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()),
                    userDao.findById(cartDto.getUserId()).orElseThrow(
                            () -> new IllegalArgumentException("User not found"))
            );
        }
    }

    public CartDto mapToCartDto(Cart cart) {
        if (cart.getId() == null) {
            throw new IllegalArgumentException("Cart not exist");
        } else {
            return new CartDto(
                    cart.getId(),
                    productMapper.mapToProductDto(cart.getProductsList()),
                    Optional.ofNullable(cart.getUser()).orElse(new User()).getId()
            );
        }
    }

    public List<CartDto> mapToListCartDto(final List<Cart> cartList) {
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
}