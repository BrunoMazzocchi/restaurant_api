package com.example.restaurant_api.persistance.repository;

import com.example.restaurant_api.persistance.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    Food save (Food food);
    void deleteById(Integer id);

    @Query(value = "SELECT * FROM food WHERE category_id = ?", nativeQuery = true)
    Optional<List<Food>> getAllByCategoryId (Integer id);
    @Query(value = "SELECT * FROM food WHERE meal_id = ?", nativeQuery = true)
    Optional<List<Food>>  getAllByMealId(Integer id);
    Optional<Food> findById(Integer id);
    List<Food> findAll();


}
