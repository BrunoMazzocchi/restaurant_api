package com.example.restaurant_api.persistance.repository;

import com.example.restaurant_api.persistance.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice, Integer> {
    Optional<UserDevice> findById(int id);

    Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);

    Optional<UserDevice> findUserDeviceByUser_UserId(int userId);

    UserDevice save(UserDevice userDevice);
}
