package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.product.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getAllProducts() {
        return IntStream.range(0, 10)
                .mapToObj(e -> new ProductDto((long) e, "TestProduct" + e))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProducts(@RequestParam long productId) {
        return new ProductDto(productId, "TestProduct" + productId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct", consumes = APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public boolean deleteProduct(@RequestParam Long productId) {
        return true;
    }
}
