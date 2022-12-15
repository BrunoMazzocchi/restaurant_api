package com.example.restaurant_api.domain.service;

import com.example.restaurant_api.persistance.dto.*;
import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.repository.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public Food save(FoodDTO foodDTO) {
        Food food = new Food();
        BeanUtils.copyProperties(foodDTO, food);
        return foodRepository.save(food);
    }

    public void deleteById(Integer id) {
        foodRepository.deleteById(id);
    }

    public Optional<List<FoodDTO>> findAllByCategoryId(Integer id) {
        List<FoodDTO> foodDTOList = new ArrayList<>();

        foodRepository.getAllByCategoryId(id).stream().map(food -> {
            FoodDTO foodDTO = new FoodDTO();
            BeanUtils.copyProperties(food, foodDTO);
            return foodDTO;
        }).forEach(foodDTOList::add);
        return Optional.of(foodDTOList);
    }

    public Optional<List<FoodDTO>> findAllByMealId(Integer id) {
        List<FoodDTO> foodDTOList = new ArrayList<>();

        foodRepository.getAllByMealId(id).stream().map(food -> {
            FoodDTO foodDTO = new FoodDTO();
            BeanUtils.copyProperties(food, foodDTO);
            return foodDTO;
        }).forEach(foodDTOList::add);
        return Optional.of(foodDTOList);
    }

    public Optional<FoodDTO> findById(Integer id) {
        FoodDTO foodDTO = new FoodDTO();
        Food food = foodRepository.findById(id).orElse(null);
        if (food != null) {
            BeanUtils.copyProperties(food, foodDTO);
            return Optional.of(foodDTO);
        }
        return foodDTO.getFood_id() != null ? Optional.of(foodDTO) : Optional.empty();
    }

    public List<FoodDTO> findAll() {
        List<FoodDTO> foodDTOList = new ArrayList<>();

        foodRepository.findAll().stream().map(food -> {
            FoodDTO foodDTO = new FoodDTO();
            BeanUtils.copyProperties(food, foodDTO);
            return foodDTO;
        }).forEach(foodDTOList::add);
        return foodDTOList;
    }
}
