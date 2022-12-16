package com.example.restaurant_api.persistance.dto;

import com.example.restaurant_api.persistance.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Data
public class UserDTO {
    private Integer userId;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String phone;
    private String image;
    private Date created_at;
    private Boolean active;
    private Set<Role> roles = new HashSet<>();

}
