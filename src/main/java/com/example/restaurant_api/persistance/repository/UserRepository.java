package com.example.restaurant_api.persistance.repository;

import com.example.restaurant_api.persistance.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
    Boolean existsByEmail(String email);

    User findUserByUserId(Integer id);

    Optional<User> findUserByNickname(String nickname);

    @Modifying
    @Transactional
    @Query(value = "update user u set u.image = ?  where u.id = ?", nativeQuery = true)
    void saveCustomerPicture(String customer_name, Integer userId);

    User save(User user);


}
