package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.cart.Cart;
import com.kodilla.ecommercee.domain.cart.dao.CartDao;
import com.kodilla.ecommercee.domain.group.Group;
import com.kodilla.ecommercee.domain.group.dao.GroupDao;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.product.ProductDto;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private ProductDao productDao;

    public Product mapToProduct(ProductDto productDto) {
        List<Cart> carts = new ArrayList<>();
        if (productDto.getId() != null) {
            Optional<Product> optionalProduct = productDao.findById(productDto.getId());
            optionalProduct.ifPresent(product -> carts.addAll(product.getCarts()));
        }
        if (productDto.getGroupId() == null) {
            productDto.setGroupId(1L);
        }
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                carts,
                groupDao.findById(productDto.getGroupId()).orElse(new Group())
        );
    }

    public ProductDto mapToProductDto(Product product) {
        if (product.getId() == null) {
            return new ProductDto();
        } else {
            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getGroupId().getId()
            );
        }
    }

    public List<ProductDto> mapToProductDto(List<Product> productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
