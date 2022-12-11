package com.example.restaurant_api.web.controller;

import com.example.restaurant_api.domain.service.*;
import com.example.restaurant_api.persistance.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/meal")
public class MealController {
    @Autowired
    private MealService mealService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Meal>> getAllMeals() {
        return new ResponseEntity<>(mealService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable("id") int id) {
        return mealService.findById(id)
                .map(meal -> new ResponseEntity<>(meal, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Meal> save(@RequestBody Meal meal){
        return new ResponseEntity<>(mealService.save(meal), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        mealService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
