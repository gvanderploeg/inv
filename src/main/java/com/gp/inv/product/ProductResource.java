package com.gp.inv.product;

import com.gp.inv.merchant.Merchant;
import com.gp.inv.merchant.MerchantRepository;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * REST resource for the product entity.
 */
@RestController
public class ProductResource {

    private static final Logger LOG = LoggerFactory.getLogger(ProductResource.class);

    private ProductRepository productRepository;
    private MerchantRepository merchantRepository;

    public ProductResource(ProductRepository productRepository, MerchantRepository merchantRepository) {
        this.productRepository = productRepository;
        this.merchantRepository = merchantRepository;
    }

    /**
     * Make the principal's merchant entity available for request mappings in this class.
     */
    @ModelAttribute
    public Merchant getUser(Principal principal) {
        return merchantRepository.findByUsername(principal.getName());
    }

    @RequestMapping("/products")
    public Iterable<Product> getAll(@ModelAttribute Merchant merchant) {
        return productRepository.findAllByMerchant(merchant);
    }

    @RequestMapping(method= PUT, path = "/products")
    @ResponseBody
    public ResponseEntity<Product> addNewProduct(UriComponentsBuilder b,
                     @ModelAttribute Merchant merchant, @Valid @RequestBody Product product) {
        product.setMerchant(merchant);
        Product savedEntity = productRepository.save(product);

        UriComponents uriComponents =
            b.path("/products/{id}").buildAndExpand(savedEntity.getId());

        return ResponseEntity
            .created(uriComponents.toUri())
            .body(savedEntity);
    }
}
