package io.github.macebot.inventory_management_api.user.dto;


public record ViewUserDto(
        Long idUser,
        String username,
        String rol
) {
}
