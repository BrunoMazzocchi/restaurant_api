package com.example.restaurant_api.persistance.data;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshRequest {
    private String refreshToken;
}