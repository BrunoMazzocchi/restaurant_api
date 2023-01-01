package com.example.restaurant_api.persistance.repository;

import com.example.restaurant_api.persistance.entity.*;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findUserByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByNickname(String nickname);

    User findUserByUserId(Integer id);

    Optional<User> findUserByNickname(String nickname);

    User save(User user);
}
