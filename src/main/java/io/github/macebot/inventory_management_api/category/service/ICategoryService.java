package io.github.macebot.inventory_management_api.category.service;

import io.github.macebot.inventory_management_api.category.dto.CreateCategoryDto;
import io.github.macebot.inventory_management_api.category.dto.UpdateCategoryDto;
import io.github.macebot.inventory_management_api.category.dto.ViewCategoryDto;
import io.github.macebot.inventory_management_api.category.entity.Category;

import java.util.List;

public interface ICategoryService {

     Category findByIdCategory(Long idCategory);
     boolean findCategoryByName(String categoryName);
     ViewCategoryDto createCategory(CreateCategoryDto newCategory);
     ViewCategoryDto viewCategory(Long idCategory);
     List<ViewCategoryDto> viewAllCategories();
     ViewCategoryDto updateCategory(Long idCategory, UpdateCategoryDto updateCategory);
     void deleteCategory(Long idCategory);

}
