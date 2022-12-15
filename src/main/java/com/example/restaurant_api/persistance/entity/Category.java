package com.example.restaurant_api.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@Table(name = "category")
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer category_id;
    @Column
    private String category_name;
    @Column
    private String category_image;
    @Column
    private String description;

    @OneToMany(mappedBy = "category_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Food> food;

}
