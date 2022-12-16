package com.example.restaurant_api.persistance.repository;

import com.example.restaurant_api.persistance.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(RoleName roleName);

    List<Role> findAll();

    Optional<Role> findById(int id);

    Role save(Role role);

    void deleteById(int id);


}
