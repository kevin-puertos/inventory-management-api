package com.kevinpuertos.inventory_management_api.user.service;

import com.kevinpuertos.inventory_management_api.user.dto.CreateUserDto;
import com.kevinpuertos.inventory_management_api.user.dto.UpdateUserDto;
import com.kevinpuertos.inventory_management_api.user.dto.ViewUserDto;
import com.kevinpuertos.inventory_management_api.user.entity.User;

import java.util.List;

public interface IUserService {
    User findUserById(Long id);
    User findUserByName(String name);
    void createUser(CreateUserDto user);
    ViewUserDto viewUser(Long id);
    List<ViewUserDto> viewAllUsers();
    ViewUserDto updateUser(UpdateUserDto updateUserDto, Long id);
    void deleteUser(Long id);


}
