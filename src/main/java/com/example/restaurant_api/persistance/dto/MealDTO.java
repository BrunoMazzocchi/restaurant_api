package com.example.restaurant_api.persistance.dto;

import lombok.*;

import java.util.*;

@Data
public class MealDTO {
    private Integer meal_id;
    private String meal_name;
    private String meal_image;
    private String description;
    private List<FoodDTO> food;
}
