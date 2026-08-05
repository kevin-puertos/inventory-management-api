package io.github.macebot.inventory_management_api.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ICustomUserDetailsServiceImpl extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
