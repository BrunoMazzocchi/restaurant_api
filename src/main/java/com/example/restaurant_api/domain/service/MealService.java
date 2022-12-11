package com.example.restaurant_api.domain.service;

import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    public Optional<Meal> findById(Integer id) {
        return mealRepository.findById(id);
    }

    public Meal save (Meal meal){
        return mealRepository.save(meal);
    }

    public  void deleteById(Integer id){
        mealRepository.deleteById(id);
    }

}
