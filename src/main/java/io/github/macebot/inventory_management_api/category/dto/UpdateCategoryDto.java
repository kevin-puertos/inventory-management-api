package io.github.macebot.inventory_management_api.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCategoryDto(
        @NotBlank(message = "Category name cannot be blank")
        String name,
        @Size(min = 4, max = 250)
        String description
) {
}
