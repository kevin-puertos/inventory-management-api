package io.github.macebot.inventory_management_api.category.dto;

public record ViewCategoryDto(
        Long idCategory,
        String name,
        String description
) {
}
