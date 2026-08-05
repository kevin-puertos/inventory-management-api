package io.github.macebot.inventory_management_api.product.dto;

import io.github.macebot.inventory_management_api.category.entity.Category;

public record ViewProductDto(
        Long idProduct,
        String name,
        Long price,
        Integer stock,
        String description,
        Category category
) {
}
