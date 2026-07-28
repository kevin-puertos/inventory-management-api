package com.kevinpuertos.inventory_management_api.auth.service;

import com.kevinpuertos.inventory_management_api.user.dto.CreateUserDto;

public interface IAuthService {
    void registerUser(CreateUserDto createUserDto);
    String login();

}
