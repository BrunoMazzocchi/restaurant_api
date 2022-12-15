package com.example.restaurant_api.domain.service;

import com.example.restaurant_api.persistance.dto.*;
import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.repository.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public List<MealDTO> findAll() {
        List<MealDTO> mealDTOList = new ArrayList<>();
        mealRepository.findAll().stream().map(meal -> {
            MealDTO mealDTO = new MealDTO();
            BeanUtils.copyProperties(meal, mealDTO);
            return mealDTO;
        }).forEach(mealDTOList::add);
        return  mealDTOList;
    }

    public Optional<MealDTO> findById(Integer id) {
        List<MealDTO> mealDTOList = new ArrayList<>();
        mealRepository.findAll().stream().map(meal -> {
            MealDTO mealDTO = new MealDTO();
            BeanUtils.copyProperties(meal, mealDTO);
            return mealDTO;
        }).forEach(mealDTOList::add);
        return mealDTOList.stream().filter(mealDTO -> mealDTO.getMeal_id().equals(id)).findFirst();
    }

    public Meal save (MealDTO mealDTO) {
        Meal meal = new Meal();
        BeanUtils.copyProperties(mealDTO, meal);
        return mealRepository.save(meal);
    }

    public void deleteById(Integer id){

        mealRepository.deleteById(id);
    }

}
