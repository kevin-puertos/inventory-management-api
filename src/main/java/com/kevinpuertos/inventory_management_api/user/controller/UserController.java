package com.kevinpuertos.inventory_management_api.user.controller;

import com.kevinpuertos.inventory_management_api.user.dto.UpdateUserDto;
import com.kevinpuertos.inventory_management_api.user.dto.ViewUserDto;
import com.kevinpuertos.inventory_management_api.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    @GetMapping("/{id}")
    public ViewUserDto viewUser(@PathVariable Long id){
        return userService.viewUser(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    @GetMapping
    public List<ViewUserDto> viewAllUsers(){
        return userService.viewAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ViewUserDto updateUser(@PathVariable Long id, @RequestBody UpdateUserDto updateUser){
        return userService.updateUser(updateUser,id);
    }

}
