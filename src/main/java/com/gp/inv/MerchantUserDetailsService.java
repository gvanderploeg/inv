package com.gp.inv;

import com.gp.inv.merchant.Merchant;
import com.gp.inv.merchant.MerchantRepository;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link UserDetailsService} that uses the {@link Merchant} entity as backend, authenticating
 * using username and passwordHash.
 */
@Component
public class MerchantUserDetailsService implements UserDetailsService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Merchant merchant = merchantRepository.findByUsername(username);
        if (merchant != null) {
            return new User(
                merchant.getUsername(),
                merchant.getPasswordHash(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
    }
}
