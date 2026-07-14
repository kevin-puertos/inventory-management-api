package com.kevinpuertos.inventory_management_api.auth.controller;

import com.kevinpuertos.inventory_management_api.user.dto.CreateUserDto;
import com.kevinpuertos.inventory_management_api.user.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid CreateUserDto createUser){
        userService.createUser(createUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }


}
