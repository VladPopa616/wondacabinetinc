package com.wondacabinetinc.wondacabinetinc.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException;
}
