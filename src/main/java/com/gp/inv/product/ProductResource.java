package com.gp.inv.product;

import com.gp.inv.merchant.AbstractMerchantOwnedResource;
import com.gp.inv.merchant.Merchant;
import com.gp.inv.merchant.MerchantRepository;

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
public class ProductResource extends AbstractMerchantOwnedResource {

    private static final Logger LOG = LoggerFactory.getLogger(ProductResource.class);

    private ProductRepository productRepository;

    public ProductResource(ProductRepository productRepository, MerchantRepository merchantRepository) {
        super(merchantRepository);
        this.productRepository = productRepository;
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
