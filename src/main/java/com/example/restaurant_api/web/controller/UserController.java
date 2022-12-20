package com.example.restaurant_api.web.controller;

import com.example.restaurant_api.domain.event.*;
import com.example.restaurant_api.domain.service.*;
import com.example.restaurant_api.persistance.data.*;
import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.entity.User;
import com.example.restaurant_api.persistance.repository.*;
import com.example.restaurant_api.web.config.exception.*;
import com.example.restaurant_api.web.response.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDeviceService userDeviceService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/me")
    public User getCurrentUser(@CurrentUser @AuthenticationPrincipal UserDetails user) {
        return userRepository.findUserByNickname(user.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getUsername()));
    }

    @PutMapping("/logout")
    public ResponseEntity<ApiResponse> logoutUser(@CurrentUser @AuthenticationPrincipal UserDetails user,
                                                  @RequestBody LogOutRequest logOutRequest) {

        System.out.println("userDevice: " + userDeviceService.findUserDeviceByUser(userRepository.findUserByNickname(user.getUsername()).get().getUserId()).map(
                device -> device.getDeviceId()).orElse("No device found"));

        String deviceId = logOutRequest.getDeviceInfo().getDeviceId();
        UserDevice userDevice = userDeviceService.findUserDeviceByUser(userRepository.findUserByNickname(user.getUsername()).get().getUserId())
                .filter(device -> device.getDeviceId().equals(deviceId))
                .orElseThrow(() -> new UserLogoutException(logOutRequest.getDeviceInfo().getDeviceId(), "Invalid device Id supplied. No matching device found for the given user "));

        refreshTokenService.deleteRefreshTokenById(userDevice.getRefreshToken().getId());



        OnUserLogoutSuccessEvent logoutSuccessEvent = new OnUserLogoutSuccessEvent(userRepository.findUserByNickname(user.getUsername()).get().getEmail(), logOutRequest.getToken(), logOutRequest);
        applicationEventPublisher.publishEvent(logoutSuccessEvent);
        return ResponseEntity.ok(new ApiResponse(true, "User has successfully logged out from the system!"));
    }

}
