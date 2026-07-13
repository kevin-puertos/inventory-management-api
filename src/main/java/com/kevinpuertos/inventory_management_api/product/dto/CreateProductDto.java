package com.kevinpuertos.inventory_management_api.product.dto;

import com.kevinpuertos.inventory_management_api.category.entity.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CreateProductDto(
        @Size(max = 30)
        @NotBlank(message = "Product name cannot be blank")
        String name,

        @Min(value = 0)
        @NotNull(message = "Product price cannot be null")
        Long price,

        @NotNull(message = "Product stock cannot be null")
        @Min(value = 0)
        Integer stock,

        @Size(max = 250)
        String description,

        Long idCategory
) {
}
