package com.example.restaurant_api.web.security;


import com.example.restaurant_api.domain.cache.*;
import com.example.restaurant_api.domain.event.*;
import com.example.restaurant_api.domain.service.*;
import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.web.config.exception.*;
import io.jsonwebtoken.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);



    private LoggedOutJwtTokenCache loggedOutJwtTokenCache;


    @Autowired
    public JwtProvider(@Lazy LoggedOutJwtTokenCache loggedOutJwtTokenCache){
        this.loggedOutJwtTokenCache = loggedOutJwtTokenCache;
    }
    public String generateJwtToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000);

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuer("StackAbuse")
                .setId(Long.toString(userPrincipal.getUserId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, "HelloWorld")
                .compact();
    }

    public String generateTokenFromUser(User user) {
        Instant expiryDate = Instant.now().plusMillis(3600000);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuer("Therapex")
                .setId(Long.toString(user.getUserId()))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS512, "HelloWorld")
                .compact();
    }

    public String getUserFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey("HelloWorld")
                .parseClaimsJws(token)
                .getBody().getId();
    }
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey("HelloWorld")
                .parseClaimsJws(token)
                .getBody().getSubject();

    }
    public String getUserIdFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey("HelloWorld")
                .parseClaimsJws(token)
                .getBody().getId();
    }

    public Date getTokenExpiryFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("HelloWorld")
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey("HelloWorld").parseClaimsJws(authToken);
            validateTokenIsNotForALoggedOutDevice(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    private void validateTokenIsNotForALoggedOutDevice(String authToken) {
        OnUserLogoutSuccessEvent previouslyLoggedOutEvent = loggedOutJwtTokenCache.getLogoutEventForToken(authToken);
        if (previouslyLoggedOutEvent != null) {
            String userEmail = previouslyLoggedOutEvent.getUserEmail();
            Date logoutEventDate = previouslyLoggedOutEvent.getEventTime();
            String errorMessage = String.format("Token corresponds to an already logged out user [%s] at [%s]. Please login again", userEmail, logoutEventDate);
            throw new InvalidTokenRequestException("JWT", authToken, errorMessage);
        }
    }

    public long getExpiryDuration() {
        return 3600000;
    }

}


