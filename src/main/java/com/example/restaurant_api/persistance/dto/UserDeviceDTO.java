package com.example.restaurant_api.persistance.dto;

import com.example.restaurant_api.persistance.entity.*;
import lombok.*;

@Data
public class UserDeviceDTO {
    private int id;
    private User user;

    private String deviceType;

    private String deviceId;


    private RefreshToken refreshToken;

    private Boolean isRefreshActive;

}
