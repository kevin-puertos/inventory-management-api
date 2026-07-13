package com.kevinpuertos.inventory_management_api.user.controller;

import com.kevinpuertos.inventory_management_api.user.dto.UpdateUserDto;
import com.kevinpuertos.inventory_management_api.user.dto.ViewUserDto;
import com.kevinpuertos.inventory_management_api.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;


    @GetMapping("/{id}")
    public ViewUserDto viewUser(@PathVariable Long id){
        return userService.viewUser(id);
    }

    @GetMapping
    public List<ViewUserDto> viewAllUsers(){
        return userService.viewAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ViewUserDto updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUser){
        return userService.updateUser(updateUser,id);
    }

}
