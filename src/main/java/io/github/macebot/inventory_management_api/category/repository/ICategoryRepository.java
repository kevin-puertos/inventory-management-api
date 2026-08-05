package io.github.macebot.inventory_management_api.category.repository;

import io.github.macebot.inventory_management_api.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);
}
