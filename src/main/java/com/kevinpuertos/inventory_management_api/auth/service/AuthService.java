package com.kevinpuertos.inventory_management_api.auth.service;


import com.kevinpuertos.inventory_management_api.user.dto.CreateUserDto;
import com.kevinpuertos.inventory_management_api.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService{
    private final IUserService userService;


    @Override
    public void registerUser(CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
    }



}
