package com.kevinpuertos.inventory_management_api.product.service;

import com.kevinpuertos.inventory_management_api.product.dto.CreateProductDto;
import com.kevinpuertos.inventory_management_api.product.dto.UpdateProductDto;
import com.kevinpuertos.inventory_management_api.product.dto.ViewProductDto;
import com.kevinpuertos.inventory_management_api.product.entity.Product;

import java.util.List;

public interface IProductService {
    Product findProductById(Long idProduct);
    Product findByProductName(String name);
    void createProduct(CreateProductDto productDto);
    ViewProductDto viewProduct(Long idProduct);
    List<ViewProductDto> viewAllProducts();
    ViewProductDto updateProduct(UpdateProductDto updateProductDto,Long idProduct);
    void deleteProduct(Long idProduct);
    ViewProductDto sellProduct(Long idProduct, Integer cantProductSold);

}
