package com.kevinpuertos.inventory_management_api.auth.service;


import com.kevinpuertos.inventory_management_api.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService{
    private final IUserService userService;





}
