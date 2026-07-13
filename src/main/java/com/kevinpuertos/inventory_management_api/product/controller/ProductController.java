package com.kevinpuertos.inventory_management_api.product.controller;

import com.kevinpuertos.inventory_management_api.product.dto.CreateProductDto;
import com.kevinpuertos.inventory_management_api.product.dto.UpdateProductDto;
import com.kevinpuertos.inventory_management_api.product.dto.ViewProductDto;
import com.kevinpuertos.inventory_management_api.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    private void createProduct(@RequestBody @Valid CreateProductDto createProductDto){
        productService.createProduct(createProductDto);
    }

    @DeleteMapping("/{id}")
    private  void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    private ViewProductDto getProductById(@PathVariable Long id){
       return productService.viewProduct(id);
    }

    @GetMapping
    private List<ViewProductDto> getProducts(){
        return productService.viewAllProducts();
    }

    @PutMapping("/{id}")
    private ViewProductDto updateProduct(@RequestBody @Valid UpdateProductDto updateProductDto, @PathVariable Long id){
        return  productService.updateProduct(updateProductDto,id);
    }
}
