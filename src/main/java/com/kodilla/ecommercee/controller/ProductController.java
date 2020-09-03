package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.product.ProductDtoStub;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDtoStub> getAllProducts() {
        return IntStream.range(0, 10)
                .mapToObj(e -> new ProductDtoStub((long) e, "TestProduct" + e))
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDtoStub getProducts(@RequestParam long productId) {
        return new ProductDtoStub(productId, "TestProduct" + productId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public ProductDtoStub createProduct(@RequestBody ProductDtoStub productDtoStub) {
        return productDtoStub;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct", consumes = APPLICATION_JSON_VALUE)
    public ProductDtoStub updateProduct(@RequestBody ProductDtoStub productDtoStub) {
        return productDtoStub;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public boolean deleteProduct(@RequestParam Long productId) {
        return true;
    }
}
