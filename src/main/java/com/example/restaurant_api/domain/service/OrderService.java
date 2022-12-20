package com.example.restaurant_api.domain.service;

import com.example.restaurant_api.persistance.dto.*;
import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.repository.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Optional<List<OrderDTO>> getOrdersByUserId(int id) {
        List<OrderDTO> orderDTOS = new ArrayList<>();

        orderRepository.findByUser_id(id).ifPresent(orders -> {
            orders.forEach(order -> {
                OrderDTO orderDTO = new OrderDTO();
                BeanUtils.copyProperties(order, orderDTO);
                orderDTOS.add(orderDTO);
            });
        });

        return Optional.of(orderDTOS);
    }

    public Optional<List<OrderDTO>> getOrdersById(int id) {
        List<OrderDTO> orderDTOS = new ArrayList<>();

        orderRepository.findByOrder_id(id).ifPresent(orders -> {
            orders.forEach(order -> {
                OrderDTO orderDTO = new OrderDTO();
                BeanUtils.copyProperties(order, orderDTO);
                orderDTOS.add(orderDTO);
            });
        });

        return Optional.of(orderDTOS);
    }

    public Order save(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        return orderRepository.save(order);
    }

}
