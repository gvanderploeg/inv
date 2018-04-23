package com.gp.inv.merchant;

import java.security.Principal;

import org.springframework.web.bind.annotation.ModelAttribute;

public class AbstractMerchantOwnedResource {

    private MerchantRepository merchantRepository;

    public AbstractMerchantOwnedResource(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    /**
     * Make the principal's merchant entity available for request mappings in this class.
     */
    @ModelAttribute
    public Merchant getUser(Principal principal) {
        return merchantRepository.findByUsername(principal.getName());
    }
}
