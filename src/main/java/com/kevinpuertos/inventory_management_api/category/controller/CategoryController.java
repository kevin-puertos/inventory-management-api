package com.kevinpuertos.inventory_management_api.category.controller;

import com.kevinpuertos.inventory_management_api.category.dto.CreateCategoryDto;
import com.kevinpuertos.inventory_management_api.category.dto.UpdateCategoryDto;
import com.kevinpuertos.inventory_management_api.category.dto.ViewCategoryDto;
import com.kevinpuertos.inventory_management_api.category.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    //Ver todas las categorias
    @GetMapping
    private List<ViewCategoryDto> viewAllCategory(){
        return categoryService.viewAllCategories();
    }

    //ver una categoria
    @GetMapping("/{idCategory}")
    private ViewCategoryDto viewCategory(@PathVariable Long idCategory){
        return categoryService.viewCategory(idCategory);
    }

    //Crear una categoria
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    private void createCategory(@RequestBody @Valid CreateCategoryDto newCategory){
        categoryService.createCategory(newCategory);
    }

    //Eliminar una categoria
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idCategory}")
    public void deleteCategory(@PathVariable Long idCategory){
        categoryService.deleteCategory(idCategory);
    }


    //Actualizar una categoria
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{idCategory}")
    private ViewCategoryDto updateCategory(@PathVariable Long idCategory, @RequestBody  @Valid UpdateCategoryDto updateCategory){
        return categoryService.updateCategory(idCategory,updateCategory);
    }




}
