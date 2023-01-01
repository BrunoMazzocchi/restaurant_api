package com.example.restaurant_api.web.controller;

import com.example.restaurant_api.domain.service.*;
import com.example.restaurant_api.persistance.data.*;
import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.repository.*;
import com.example.restaurant_api.web.response.*;
import com.example.restaurant_api.web.security.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.*;

import java.net.*;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    UserDeviceService userDeviceService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleService roleService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {


        User user = userRepository.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not found."));



        if (user.getActive()) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getNickname(),
                            loginRequest.getPassword()
                    )
            );



            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtProvider.generateJwtToken(authentication);
            userDeviceService.findUserDeviceByUser(user.getUserId())
                    .map(UserDevice::getRefreshToken)
                    .map(RefreshToken::getId)
                    .ifPresent(refreshTokenService::deleteRefreshTokenById);



            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setDeviceId(loginRequest.getDeviceInfo().getDeviceId());
            deviceInfo.setDeviceType(loginRequest.getDeviceInfo().getDeviceType());


            //Dev test, still waiting to implement user device creation
            UserDevice userDevice = userDeviceService.createUserDevice(deviceInfo);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken();


            userDevice.setUser(user);
            userDevice.setRefreshToken(refreshToken);

            refreshToken.setUserDevice(userDevice);
            refreshToken = refreshTokenService.save(refreshToken);

            userDeviceService.saveUserDevice(userDevice);

            return ResponseEntity.ok(new JwtResponse(jwtToken, refreshToken.getToken(), jwtProvider.getExpiryDuration()));
        }
        return ResponseEntity.badRequest().body(new ApiResponse(false, "User has been deactivated/locked !!"));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> verifyToken(@RequestBody LogOutRequest token) {
        if (jwtProvider.validateJwtToken(token.getToken())) {
            return ResponseEntity.ok(new ApiResponse(true, "Token is valid"));
        }
        return ResponseEntity.badRequest().body(new ApiResponse(false, "Token is invalid"));
    }

    @PostMapping("/signup")

    // TODO : Avoid duplicate email/nickname
    public ResponseEntity<?> registerUser(@RequestBody SignUpUserRequest signUpRequest) {
        if(userService.userExistsByEmail(signUpRequest.getEmail())){
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        } else if ( userService.userExistsByNickname(signUpRequest.getNickname())) {
            return new ResponseEntity<String>("Fail -> Nickname is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        user.setImage("https://www.pngmart.com/files/21/Account-Avatar-Profile-PNG-Clipart.png");
        user.setPhone(signUpRequest.getPhone());
        user.setNickname(signUpRequest.getNickname());
        Set<String> strRoles = Collections.singleton(signUpRequest.getRole());
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch(role) {
                case "admin":
                    Role adminRole = roleService.findByRoleName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
                    roles.add(adminRole);

                    break;
                default:
                    Role userRole = roleService.findByRoleName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not found."));
                    roles.add(userRole);
            }
        });

        user.setRoles(roles);
        user.activate();
        User result = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getUserId()).toUri();

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
