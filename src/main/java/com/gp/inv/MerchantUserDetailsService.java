package com.gp.inv;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MerchantUserDetailsService implements UserDetailsService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Merchant merchant = merchantRepository.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("User '" + username + "' not found"));

        return new User(
            merchant.getUsername(),
            merchant.getPasswordHash(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
