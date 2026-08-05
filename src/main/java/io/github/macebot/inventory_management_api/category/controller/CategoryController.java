package io.github.macebot.inventory_management_api.category.controller;

import io.github.macebot.inventory_management_api.category.dto.CreateCategoryDto;
import io.github.macebot.inventory_management_api.category.dto.UpdateCategoryDto;
import io.github.macebot.inventory_management_api.category.dto.ViewCategoryDto;
import io.github.macebot.inventory_management_api.category.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @PostMapping
    private ViewCategoryDto createCategory(@RequestBody @Valid CreateCategoryDto newCategory){
        return categoryService.createCategory(newCategory);
    }

    //Eliminar una categoria
    @DeleteMapping("/{idCategory}")
    public void deleteCategory(@PathVariable Long idCategory){
        categoryService.deleteCategory(idCategory);
    }


    //Actualizar una categoria
    @PutMapping("/{idCategory}")
    private ViewCategoryDto updateCategory(@PathVariable Long idCategory, @RequestBody  @Valid UpdateCategoryDto updateCategory){
        return categoryService.updateCategory(idCategory,updateCategory);
    }




}
