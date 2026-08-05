package io.github.macebot.inventory_management_api.product.repository;

import io.github.macebot.inventory_management_api.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
}
