package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.group.dao.GroupDao;
import com.kodilla.ecommercee.domain.order.Order;
import com.kodilla.ecommercee.domain.order.dao.OrderDao;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.product.ProductDto;
import com.kodilla.ecommercee.domain.product.ProductDtoStub;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import com.kodilla.ecommercee.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final GroupDao groupDao;
    private final ProductDao productDao;
    private final CartDao cartDao;
    private final OrderDao orderDao;

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroupId().getName()
        );
    }

    public Product mapToProduct(ProductDto productDto) {
        return productDao.findById(productDto.getId()).orElseGet(
                () -> new Product(
                        productDto.getName(),
                        productDto.getDescription(),
                        productDto.getPrice(),
                        groupDao.findByName(productDto.getGroupName())
                ));
    }

    public List<ProductDto> mapToProductDtoList(List<Product> product) {
        return product.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    public List<Product> mapToProductList(List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }
}
