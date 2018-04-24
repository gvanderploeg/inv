package com.gp.inv.productstock;

import com.gp.inv.product.Product;

import java.util.stream.Stream;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductStockRepository extends PagingAndSortingRepository<ProductStock, Long> {

    Iterable<ProductStock> findByProduct(Product p);

    Stream<ProductStock> findByNrInStockGreaterThan(int i);

}
