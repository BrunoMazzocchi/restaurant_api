package com.example.restaurant_api.persistance.dto;

import lombok.*;

@Data
public class FoodDTO {
    private Integer food_id;
    private String food_name;
    private String description;
    private String food_image;
    private double price;
    private double discount;
    private double rating;
    private double calories;
    private double preparation_time;
    private Integer category_id;
    private Integer meal_id;
}
