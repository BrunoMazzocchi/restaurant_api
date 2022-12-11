package com.example.restaurant_api.persistance.repository;

import com.example.restaurant_api.persistance.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category>  findAll();
    Optional<Category> findById(Integer id);
    Category save (Category category);
    void deleteById(Integer id);

}
