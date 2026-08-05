package io.github.macebot.inventory_management_api.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginUserDto(
        @NotNull
        @Size(min = 3, max = 30)
        String username,

        @NotNull
        @Size(min = 3, max = 30)
        String password
) {
}
