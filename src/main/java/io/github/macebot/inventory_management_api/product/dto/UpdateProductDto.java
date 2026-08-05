package io.github.macebot.inventory_management_api.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UpdateProductDto(
        @Size(max = 30,message = "The name cannot exceed 30 characters")
        String name,

        @Min(value = 0,message = "The Price Field cannot be less than 0")
        Long price,

        @Min(value = 0,message = "The Stock Field cannot be less than 0")
        Integer stock,

        @Size(max = 250,message ="The name cannot exceed 30 characters")
        String description,

        Long idCategory
) {
}
