package com.example.restaurant_api.persistance.repository;

import com.example.restaurant_api.persistance.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    Food save (Food food);
    void deleteById(Integer id);
    Optional<List<Food>>  findAllByCategoryId(Integer id);
    Optional<List<Food>>  findAllByMealId(Integer id);
    Optional<Food> findById(Integer id);


}
