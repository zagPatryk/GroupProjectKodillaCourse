package com.kodilla.ecommercee.domain.cart;

import com.kodilla.ecommercee.domain.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long cartId;
    private List<ProductDto> products;
    private Long userId;
}
