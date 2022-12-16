package com.example.restaurant_api.persistance.repository;

import com.example.restaurant_api.persistance.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

        Optional<RefreshToken> findById(int id);

        Optional<RefreshToken> findByToken(String token);

        void deleteById(int id);

}
