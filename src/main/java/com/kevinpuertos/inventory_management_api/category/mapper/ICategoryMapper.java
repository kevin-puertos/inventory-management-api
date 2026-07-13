package com.kevinpuertos.inventory_management_api.category.mapper;


import com.kevinpuertos.inventory_management_api.category.dto.CreateCategoryDto;
import com.kevinpuertos.inventory_management_api.category.dto.ViewCategoryDto;
import com.kevinpuertos.inventory_management_api.category.entity.Category;
import com.kevinpuertos.inventory_management_api.product.mapper.IProductMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IProductMapper.class)
public interface ICategoryMapper
{
    Category ToCategory(CreateCategoryDto newCategory);
    ViewCategoryDto ToViewCategoryDto(Category category);
    List<ViewCategoryDto> ToViewDtoList(List<Category> categoryList);


}


