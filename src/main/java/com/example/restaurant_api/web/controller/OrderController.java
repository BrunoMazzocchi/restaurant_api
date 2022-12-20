package com.example.restaurant_api.web.controller;

import com.example.restaurant_api.domain.service.*;
import com.example.restaurant_api.persistance.dto.*;
import org.apache.coyote.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable int id) {
        return new ResponseEntity<>(orderService.getOrdersByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrdersById(@PathVariable int id) {
        return new ResponseEntity<>(orderService.getOrdersById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.save(orderDTO), HttpStatus.CREATED);
    }

}
