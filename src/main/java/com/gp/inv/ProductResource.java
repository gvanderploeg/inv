package com.gp.inv;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductResource {

    public ProductResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductRepository productRepository;

    @RequestMapping("/products")
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }
}
