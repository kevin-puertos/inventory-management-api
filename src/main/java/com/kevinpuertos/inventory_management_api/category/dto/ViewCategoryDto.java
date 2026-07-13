package com.kevinpuertos.inventory_management_api.category.dto;

public record ViewCategoryDto(
        Long idCategory,
        String name,
        String description
) {
}
