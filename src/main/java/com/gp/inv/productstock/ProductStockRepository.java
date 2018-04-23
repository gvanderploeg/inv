package com.gp.inv.productstock;

import com.gp.inv.product.Product;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductStockRepository extends PagingAndSortingRepository<ProductStock, Long> {

    Iterable<ProductStock> findByProduct(Product p);

}
