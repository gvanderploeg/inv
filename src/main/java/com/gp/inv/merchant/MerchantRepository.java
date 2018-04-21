package com.gp.inv.merchant;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface MerchantRepository extends PagingAndSortingRepository<Merchant, Long> {

    Merchant findByUsername(@Param("username") String username);
}