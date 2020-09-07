package com.kodilla.ecommercee.domain.order;

import com.kodilla.ecommercee.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private List<Product> products;
    private Long userId;
}
