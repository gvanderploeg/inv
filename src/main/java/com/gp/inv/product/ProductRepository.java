package com.gp.inv.product;


import com.gp.inv.merchant.Merchant;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Products.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAllByMerchant(Merchant merchant);

    Product findByMerchantAndId(Merchant merchant, long productId);
}
