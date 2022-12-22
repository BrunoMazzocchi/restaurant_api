package com.example.restaurant_api.web.controller;

import com.example.restaurant_api.domain.service.*;
import com.example.restaurant_api.persistance.dto.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{orderStatus}")
    public ResponseEntity<?> getOrdersByUserId(@CurrentUser @AuthenticationPrincipal UserDetails user, @PathVariable String orderStatus) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(user.getUsername(), orderStatus));
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
