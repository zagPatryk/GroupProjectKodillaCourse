package com.kodilla.ecommercee.controller;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.product.ProductDto;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getAllProducts() {
        return productMapper.mapToProductDto(productService.getAllProducts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProducts(@RequestParam long productId) {
        return productMapper.mapToProductDto(productService.getProduct(productId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productService.saveProduct(productMapper.mapToProduct(productDto));
        return productMapper.mapToProductDto(product);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct", consumes = APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productMapper.mapToProductDto(productService.updateProduct(productMapper.mapToProduct(productDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public boolean deleteProduct(@RequestParam Long productId) {
        return productService.deleteProduct(productId);
    }
}
