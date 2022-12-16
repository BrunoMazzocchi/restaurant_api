package com.example.restaurant_api.domain.service;

import com.example.restaurant_api.persistance.data.*;
import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserDeviceService {
    @Autowired
    private UserDeviceRepository userDeviceRepository;

    public Optional<UserDevice> findUserDeviceById(int id ) {
        return userDeviceRepository.findById(id);
    }

    public Optional<UserDevice> findUserDeviceByRefreshToken(RefreshToken refreshToken) {
        return userDeviceRepository.findByRefreshToken(refreshToken);
    }

    public Optional<UserDevice> findUserDeviceByUser(int userId) {
        return userDeviceRepository.findUserDeviceByUser_UserId(userId);
    }

    public UserDevice createUserDevice(DeviceInfo deviceInfo) {
        UserDevice userDevice = new UserDevice();
        userDevice.setDeviceId(deviceInfo.getDeviceId());
        userDevice.setDeviceType(deviceInfo.getDeviceType());
        userDevice.setIsRefreshActive(true);
        return userDevice;
    }

}
