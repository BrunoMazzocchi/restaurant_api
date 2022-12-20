package com.example.restaurant_api.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@Table(name = "food")
public class Food {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer food_id;
    @Column
    private String food_name;
    @Column
    private String description;
    @Column
    private String food_image;
    @Column
    private double price;
    @Column
    private double discount;
    @Column
    private double rating;
    @Column
    private double calories;
    @Column
    private double preparation_time;
    @Column
    private Integer category_id;
    @Column
    private Integer meal_id;

}
