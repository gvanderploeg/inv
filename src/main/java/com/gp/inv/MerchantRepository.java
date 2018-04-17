package com.gp.inv;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface MerchantRepository extends PagingAndSortingRepository<Merchant, Long> {

    Optional<Merchant> findByUsername(@Param("username") String username);
}