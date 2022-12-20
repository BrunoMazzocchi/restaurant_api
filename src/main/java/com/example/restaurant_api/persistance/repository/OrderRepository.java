package com.example.restaurant_api.persistance.repository;

import com.example.restaurant_api.persistance.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders WHERE user_id = ?1", nativeQuery = true)
    Optional<List<Order>> findByUser_id(int user);
    @Query(value = "SELECT * FROM orders WHERE order_id = ?1", nativeQuery = true)
    Optional<List<Order>>  findByOrder_id(Integer id);
    Order save(Order order);
}
