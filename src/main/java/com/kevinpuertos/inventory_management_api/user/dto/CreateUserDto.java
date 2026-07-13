package com.kevinpuertos.inventory_management_api.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotNull
        @Size(min = 3, max = 30)
        String userName,

        @NotNull
        @Pattern(regexp = "ADMIN|EMPLOYE",message = "Invalid rol, allowed status: ADMIN or EMPLOYE")
        String userRole,

        @NotNull
        @Size(min = 3, max = 30)
        String password
) {
}
