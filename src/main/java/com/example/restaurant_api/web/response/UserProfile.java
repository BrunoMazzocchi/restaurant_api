package com.example.restaurant_api.web.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean active;
}