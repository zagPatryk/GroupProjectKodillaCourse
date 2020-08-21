package com.kodilla.ecommercee.domain.cart;

import com.kodilla.ecommercee.domain.product.ProductDtoStub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private List<ProductDtoStub> cart; // change to List<ProductDto>
}
