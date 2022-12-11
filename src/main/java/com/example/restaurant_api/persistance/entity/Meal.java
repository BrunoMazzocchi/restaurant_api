package com.example.restaurant_api.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@Table(name = "meal")
public class Meal {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer meal_id;
    @Column
    private String meal_name;
    @Column
    private String meal_image;
    @Column
    private String description;

    @OneToMany(mappedBy = "mealId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Food> food;
}
