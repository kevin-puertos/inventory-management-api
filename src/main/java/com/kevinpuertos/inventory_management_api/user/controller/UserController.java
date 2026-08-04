package com.kevinpuertos.inventory_management_api.user.controller;

import com.kevinpuertos.inventory_management_api.user.dto.UpdateUserDto;
import com.kevinpuertos.inventory_management_api.user.dto.ViewUserDto;
import com.kevinpuertos.inventory_management_api.user.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    @GetMapping("/admin/{id}")
    public ViewUserDto viewUser(@PathVariable Long id){
        return userService.viewUser(id);
    }

    @PreAuthorize("hasRole('ADMIN'))")
    @GetMapping("/admin")
    public List<ViewUserDto> viewAllUsers(){
        return userService.viewAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{id}")
    public ViewUserDto updateUser(@PathVariable  Long id, @RequestBody @Valid UpdateUserDto updateUser){
        return userService.updateUser(updateUser,id);
    }

}
