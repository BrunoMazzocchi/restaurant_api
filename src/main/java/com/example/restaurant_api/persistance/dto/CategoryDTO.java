package com.example.restaurant_api.persistance.dto;

import lombok.*;

import java.util.*;

@Data
public class CategoryDTO {
    private Integer category_id;
    private String category_name;
    private String category_image;
    private String description;
    private List<FoodDTO> food;
}
