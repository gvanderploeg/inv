package com.gp.inv;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductResource {

    private static final Logger LOG = LoggerFactory.getLogger(ProductResource.class);

    public ProductResource(ProductRepository productRepository, MerchantRepository merchantRepository) {
        this.productRepository = productRepository;
        this.merchantRepository = merchantRepository;
    }

    private ProductRepository productRepository;
    private MerchantRepository merchantRepository;

    @RequestMapping("/products")
    public Iterable<Product> getAll(Principal principal) {
        LOG.debug("getAll() for user " + principal.getName());

        return productRepository.findAllByMerchant(merchantRepository.findByUsername(principal.getName()));
    }


}
