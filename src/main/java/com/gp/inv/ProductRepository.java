package com.gp.inv;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findByName(@Param("name") String name);
}
