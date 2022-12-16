package com.example.restaurant_api.domain.service;

import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role findById(int id){
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public void deleteById(int id){
        roleRepository.deleteById(id);
    }

    public  Optional<Role> findByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }
}
