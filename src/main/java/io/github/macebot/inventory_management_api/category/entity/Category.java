package io.github.macebot.inventory_management_api.category.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.macebot.inventory_management_api.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idCategory;

    @Column(nullable = false, unique = true)
    private  String name;

    String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
