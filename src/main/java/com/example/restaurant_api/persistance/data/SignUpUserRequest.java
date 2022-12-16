package com.example.restaurant_api.persistance.data;

import com.example.restaurant_api.persistance.entity.*;
import lombok.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpUserRequest
{
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String phone;
    private String role;
}
