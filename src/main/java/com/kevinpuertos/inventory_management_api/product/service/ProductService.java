package com.kevinpuertos.inventory_management_api.product.service;

import com.kevinpuertos.inventory_management_api.category.entity.Category;
import com.kevinpuertos.inventory_management_api.category.service.ICategoryService;
import com.kevinpuertos.inventory_management_api.exceptions.ResourceAlreadyExistException;
import com.kevinpuertos.inventory_management_api.exceptions.ResourceNotFoundException;
import com.kevinpuertos.inventory_management_api.product.dto.CreateProductDto;
import com.kevinpuertos.inventory_management_api.product.dto.UpdateProductDto;
import com.kevinpuertos.inventory_management_api.product.dto.ViewProductDto;
import com.kevinpuertos.inventory_management_api.product.entity.Product;
import com.kevinpuertos.inventory_management_api.product.mapper.IProductMapper;
import com.kevinpuertos.inventory_management_api.product.repository.IProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{


    private final IProductRepository productRepository;
    private final IProductMapper productMapper;
    private final ICategoryService categoryService;

    @Override
    public Product findProductById(Long idProduct) {
        return productRepository.findById(idProduct).orElseThrow(() -> new ResourceNotFoundException("The product with id: "+idProduct+" doesn't exist")) ;
    }

    @Override
    public Product findByProductName(String name) {
        return productRepository.findByName(name);
    }


    @Override
    public void createProduct(CreateProductDto productDto) {
        Product product = findByProductName(productDto.name());

        if(product != null){
            throw new ResourceAlreadyExistException("Product already exist");
        }

        Product product1 = new Product();
        product1 = productMapper.createDtoToProduct(productDto);
        Category category = categoryService.findByIdCategory(productDto.idCategory());
        product1.setCategory(category);

        productRepository.save(product1);

    }

    @Override
    public ViewProductDto viewProduct( Long idProduct) {
        return productMapper.productTOViewDto(productRepository.findById(idProduct).orElseThrow( () -> new ResourceNotFoundException("Product not exist")));
    }

    @Override
    public List<ViewProductDto> viewAllProducts() {
        return productMapper.productListToDto( productRepository.findAll());
    }

    @Override
    public ViewProductDto updateProduct(UpdateProductDto updateProductDto, Long idProduct) {

        Product product = this.findProductById(idProduct);

        if(updateProductDto.name() != null){
            Product productAux = this.findByProductName(updateProductDto.name());
            if(productAux != null){
                throw new ResourceAlreadyExistException("Product name already exist");
            }
            product.setName(updateProductDto.name());
        }
        if(updateProductDto.idCategory() != null){
            Category category = categoryService.findByIdCategory(updateProductDto.idCategory());
            product.setCategory(category);
        }
        if(updateProductDto.description() != null){
            product.setDescription(updateProductDto.description());
        }
        if(updateProductDto.price() != null){
            product.setPrice(updateProductDto.price());
        }
        if(updateProductDto.stock() != null){
            product.setStock(updateProductDto.stock());
        }

        productRepository.save(product);

        return productMapper.productTOViewDto(product);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        Product product = this.findProductById(idProduct);
        productRepository.delete(product);
    }

    @Override
    public ViewProductDto sellProduct(Long idProduct, Integer cantProductSold) {
        Product product = this.findProductById(idProduct);

        if(cantProductSold > product.getStock()){

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT,
                    "There is not enough stock of the selected product to complete this action.");

        }

        product.setStock(product.getStock() - cantProductSold);
        //Fala aplicar un registro de ventas

        return productMapper.productTOViewDto( productRepository.save(product));
    }


}
