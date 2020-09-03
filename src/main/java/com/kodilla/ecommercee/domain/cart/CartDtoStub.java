package com.kodilla.ecommercee.domain.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDtoStub {
    private List<String> cart; //List<ProductDto>
}
