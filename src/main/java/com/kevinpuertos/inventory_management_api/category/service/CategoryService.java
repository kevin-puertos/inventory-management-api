package com.kevinpuertos.inventory_management_api.category.service;

import com.kevinpuertos.inventory_management_api.category.dto.CreateCategoryDto;
import com.kevinpuertos.inventory_management_api.category.dto.UpdateCategoryDto;
import com.kevinpuertos.inventory_management_api.category.dto.ViewCategoryDto;
import com.kevinpuertos.inventory_management_api.category.entity.Category;
import com.kevinpuertos.inventory_management_api.category.mapper.ICategoryMapper;
import com.kevinpuertos.inventory_management_api.category.repository.ICategoryRepository;
import com.kevinpuertos.inventory_management_api.exceptions.ResourceAlreadyExistException;
import com.kevinpuertos.inventory_management_api.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final ICategoryMapper categoryMapper;
    private final ICategoryRepository categoryRepository;


    @Override
    public Category findByIdCategory(Long idCategory) {
        return categoryRepository.findById(idCategory).orElseThrow(() -> new ResourceNotFoundException("The category does not exist"));
    }

    @Override
    public boolean findCategoryByName(String categoryName) {
        return categoryRepository.existsByName(categoryName);
    }

    @Override
    public ViewCategoryDto createCategory(CreateCategoryDto newCategory) {

        if(!this.findCategoryByName(newCategory.name())){
            return  categoryMapper.ToViewCategoryDto(categoryRepository.save(categoryMapper.ToCategory(newCategory)));

        }
        throw new ResourceAlreadyExistException("Category already exist");
    }

    @Override
    public ViewCategoryDto viewCategory(Long idCategory) {
        return  categoryMapper.ToViewCategoryDto(this.findByIdCategory(idCategory));
    }

    @Override
    public List<ViewCategoryDto> viewAllCategories() {
        return categoryMapper.ToViewDtoList(categoryRepository.findAll());
    }

    @Override
    public ViewCategoryDto updateCategory(Long idCategory, UpdateCategoryDto updateCategory) {

        Category category = this.findByIdCategory(idCategory);

        if(updateCategory.name() != null){
            category.setName(updateCategory.name());
        }
        if(updateCategory.description() != null){
            category.setDescription(updateCategory.description());
        }

       return categoryMapper.ToViewCategoryDto(categoryRepository.save(category));

    }

    @Override
    public void deleteCategory(Long idCategory) {
        Category category = this.findByIdCategory(idCategory);

        categoryRepository.delete(category);
    }
}
