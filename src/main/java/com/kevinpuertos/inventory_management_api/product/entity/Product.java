package com.kevinpuertos.inventory_management_api.product.entity;

import com.kevinpuertos.inventory_management_api.category.entity.Category;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idProduct;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Integer stock;

    private String description;

    @ManyToOne
    private Category category;


}
