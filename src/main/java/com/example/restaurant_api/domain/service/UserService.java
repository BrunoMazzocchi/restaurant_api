package com.example.restaurant_api.domain.service;

import com.example.restaurant_api.persistance.dto.*;
import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.repository.*;
import jakarta.persistence.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    private EntityManager entityManager;

    public UserDTO findUserByUserId(int id) {
        UserDTO customerDTO = new UserDTO();
        User customer = userRepository.findUserByUserId(id);

        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Optional<UserDTO> findUserByEmail(String email) {
        UserDTO customerDTO = new UserDTO();
        Optional<User> customer = userRepository.findUserByEmail(email);

        BeanUtils.copyProperties(customer, customerDTO);
        return Optional.of(customerDTO);
    }

    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean userExistsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public User save (User user) {
        return userRepository.save(user);
    }
}
