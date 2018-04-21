package com.gp.inv.product;


import com.gp.inv.merchant.Merchant;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository for Products.
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByMerchant(Merchant merchant);
}
