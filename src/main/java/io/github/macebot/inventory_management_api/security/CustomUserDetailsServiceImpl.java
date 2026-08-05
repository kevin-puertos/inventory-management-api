package io.github.macebot.inventory_management_api.security;

import io.github.macebot.inventory_management_api.exceptions.ResourceNotFoundException;
import io.github.macebot.inventory_management_api.user.entity.User;
import io.github.macebot.inventory_management_api.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements ICustomUserDetailsServiceImpl {
    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {


        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );

        //Construyo y retorno el objeto de UserDetails con los datos del usuario
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_"+user.getRol().name()))
                .build();
    }
}
