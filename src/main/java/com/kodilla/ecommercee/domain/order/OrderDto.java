package com.kodilla.ecommercee.domain.order;

import com.kodilla.ecommercee.domain.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private List<ProductDto> products;
    private Long userId;
}
