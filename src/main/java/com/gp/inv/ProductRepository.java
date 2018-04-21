package com.gp.inv;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository for Products.
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAllByMerchant(Merchant merchant);
}
