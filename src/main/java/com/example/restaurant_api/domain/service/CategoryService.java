package com.example.restaurant_api.domain.service;

import com.example.restaurant_api.persistance.dto.*;
import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.repository.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        List<FoodDTO> foodDTOS = new ArrayList<>();


        categoryRepository.findAll().stream().map(category -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(category, categoryDTO);
            category.getFood().stream().map(food -> {
                FoodDTO foodDTO = new FoodDTO();
                BeanUtils.copyProperties(food, foodDTO);
                return foodDTO;
            }).forEach(foodDTOS::add);

            categoryDTO.setFood(foodDTOS);
            return categoryDTO;
        }).forEach(categoryDTOS::add);

        return categoryDTOS;
    }

    public Optional<CategoryDTO> findById(Integer id) {
        CategoryDTO categoryDTO = new CategoryDTO();
        List<FoodDTO> foodDTOS = new ArrayList<>();

        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.getFood().stream().map(food -> {
                FoodDTO foodDTO = new FoodDTO();
                BeanUtils.copyProperties(food, foodDTO);
                return foodDTO;
            }).forEach(foodDTOS::add);

            BeanUtils.copyProperties(category, categoryDTO);

            categoryDTO.setFood(foodDTOS);
            return Optional.of(categoryDTO);
        }
        return categoryDTO.getCategory_id() != null ? Optional.of(categoryDTO) : Optional.empty();
    }

    public Category save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        return categoryRepository.save(category);
    }

    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

}
