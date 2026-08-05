package io.github.macebot.inventory_management_api.product.controller;

import io.github.macebot.inventory_management_api.product.dto.CreateProductDto;
import io.github.macebot.inventory_management_api.product.dto.UpdateProductDto;
import io.github.macebot.inventory_management_api.product.dto.ViewProductDto;
import io.github.macebot.inventory_management_api.product.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    private ViewProductDto updateProduct(
            @RequestBody @Valid UpdateProductDto updateProductDto,
            @PathVariable Long id)
    {
        return  productService.updateProduct(updateProductDto,id);
    }

    @PostMapping("/sell/{idProduct}/{cantProduct}")
    public ViewProductDto sellProduct(
            @PathVariable
            @NotNull(message = "The product ID cannot be null")
            Long idProduct,
            @PathVariable
            @NotNull(message =  "The quantity of products sold cannot be null")
            @Min(value = 0, message = "The quantity of products sold cannot be less than 0")
            Integer cantProduct)
    {
            return productService.sellProduct(idProduct,cantProduct);

    }



    //Falta modificar el stock(Un empleado puede hacerlo) /products/{idProduct}/nuevo stock
}
