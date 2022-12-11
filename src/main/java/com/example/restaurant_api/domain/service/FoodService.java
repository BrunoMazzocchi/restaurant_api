package com.example.restaurant_api.domain.service;

import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public Food save (Food food){
        return foodRepository.save(food);
    }

    public void deleteById(Integer id){
        foodRepository.deleteById(id);
    }

    public Optional<List<Food>> findAllByCategoryId(Integer id) {
        return this.foodRepository.findAllByCategoryId(id);
    }

    public Optional<List<Food>> findAllByMealId(Integer id) {
        return foodRepository.findAllByMealId(id);
    }
    public  Optional<Food> findById(Integer id) {
        return foodRepository.findById(id);
    }
}
