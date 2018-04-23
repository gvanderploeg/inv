package com.gp.inv.productstock;

import com.gp.inv.merchant.AbstractMerchantOwnedResource;
import com.gp.inv.merchant.Merchant;
import com.gp.inv.merchant.MerchantRepository;
import com.gp.inv.product.Product;
import com.gp.inv.product.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductStockResource extends AbstractMerchantOwnedResource {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractMerchantOwnedResource.class);

    private ProductRepository productRepository;
    private ProductStockRepository productStockRepository;

    public ProductStockResource(ProductRepository productRepository,
                                ProductStockRepository productStockRepository, MerchantRepository merchantRepository) {
        super(merchantRepository);
        this.productRepository = productRepository;
        this.productStockRepository = productStockRepository;
    }

    @RequestMapping("/products/{id}/stock")
    public Iterable<ProductStock> getProductStock(@ModelAttribute Merchant merchant, @PathVariable("id") long productId) {
        Product p = productRepository.findByMerchantAndId(merchant, productId);
        return productStockRepository.findByProduct(p);
    }
}
