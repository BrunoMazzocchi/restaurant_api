package com.example.restaurant_api.web.controller;

import com.example.restaurant_api.domain.service.*;
import com.example.restaurant_api.persistance.dto.*;
import com.example.restaurant_api.persistance.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping(value = "/save")
    public ResponseEntity<Food> save(@RequestBody FoodDTO foodDTO) {
        return new ResponseEntity<>(foodService.save(foodDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        foodService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/byCategory/{categoryId}")
    public ResponseEntity<List<FoodDTO>> getFoodsByCategory(@PathVariable("categoryId") int categoryId) {
        return foodService.findAllByCategoryId(categoryId)
                .map(foods -> new ResponseEntity<>(foods, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/byMeal/{mealId}")
    public ResponseEntity<List<FoodDTO>> getFoodsByMeal(@PathVariable("mealId") int mealId) {
        return foodService.findAllByMealId(mealId)
                .map(foods -> new ResponseEntity<>(foods, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FoodDTO> getFoodById(@PathVariable("id") int id) {
        return foodService.findById(id)
                .map(food -> new ResponseEntity<>(food, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<FoodDTO>> getAllFoods() {
        return new ResponseEntity<>(foodService.findAll(), HttpStatus.OK);
    }
}
