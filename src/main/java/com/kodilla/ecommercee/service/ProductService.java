package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.product.Product;
import com.kodilla.ecommercee.domain.product.dao.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> getAllProducts() {
        List<Product> productsList = new ArrayList<>(productDao.findAll());
        return productsList.stream().filter(Product::isActive).collect(Collectors.toList());
    }

    public Product getProduct(Long productId) {
        Product product = productDao.findById(productId).orElse(new Product());
        if (product.isActive()) {
            return product;
        } else {
            return new Product();
        }
    }

    public Product saveProduct(Product product) {
        productDao.save(product);
        return product;
    }

    public Product updateProduct(Product product) {
        if (productDao.existsById(product.getId())) {
            product.setActive(productDao.findById(product.getId()).get().isActive());
        }
        productDao.save(product);
        return product;
    }

    public boolean deleteProduct(Long productId) {
        if (productDao.existsById(productId)) {
            Product product = productDao.findById(productId).get();
            product.setActive(false);
            productDao.save(product);
            return true;
        } else {
            return false;
        }
    }
}
