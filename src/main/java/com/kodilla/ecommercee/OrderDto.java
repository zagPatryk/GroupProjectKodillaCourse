package com.kodilla.ecommercee;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDto {
    public Long orderId;
    public String nameOrder;
}
