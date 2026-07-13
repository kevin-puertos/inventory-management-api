package com.kevinpuertos.inventory_management_api.product.dto;

import com.kevinpuertos.inventory_management_api.category.entity.Category;

public record ViewProductDto(
        Long idProduct,
        String name,
        Long price,
        Integer stock,
        String description,
        Category category
) {
}
