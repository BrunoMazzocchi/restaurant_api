package com.example.restaurant_api.persistance.repository;

import com.example.restaurant_api.persistance.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {
    List<Meal> findAll();
    Optional<Meal> findById(Integer id);
    Meal save (Meal meal);
    void deleteById(Integer id);
}
