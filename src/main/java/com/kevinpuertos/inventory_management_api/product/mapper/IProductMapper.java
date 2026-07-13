package com.kevinpuertos.inventory_management_api.product.mapper;

import com.kevinpuertos.inventory_management_api.category.mapper.ICategoryMapper;
import com.kevinpuertos.inventory_management_api.product.dto.CreateProductDto;
import com.kevinpuertos.inventory_management_api.product.dto.ViewProductDto;
import com.kevinpuertos.inventory_management_api.product.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ICategoryMapper.class)
public interface IProductMapper {
    Product createDtoToProduct(CreateProductDto createProductDto);
    ViewProductDto productTOViewDto(Product product);
    List<ViewProductDto>  productListToDto(List<Product> products);

}
