package com.example.restaurant_api.persistance.data;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    // Payload Validators
    private String email;
    private String password;
    private DeviceInfo deviceInfo;
}
