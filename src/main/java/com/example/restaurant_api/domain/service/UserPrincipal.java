package com.example.restaurant_api.domain.service;

import com.example.restaurant_api.persistance.entity.*;
import com.example.restaurant_api.persistance.entity.User;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.sql.Date;
import java.util.*;
import java.util.stream.*;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements UserDetails {
    private static final Integer serialVersionUID = 1;
    private Integer userId;
    private String name;
    private String nickname;
    private String email;
    private String phone;
    private String image;
    private Date created_at;
    private Boolean active;
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;


    public UserPrincipal(int userId, String name, String nickname, String email, String password, String phone, String image, Date created_at, Boolean active, Set<Role> roles, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.image = image;
        this.created_at = created_at;
        this.active = active;
        this.roles = roles;
        this.authorities = authorities;
    }

    public static  UserPrincipal build (User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getRoleName().name())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getUserId(),
                user.getName(),
                user.getNickname(),
                user.getEmail(),
                user.getPassword(),
                user.getImage(),
                user.getPhone(),
                user.getCreated_at(),
                user.getActive(),
                user.getRoles(),
                authorities
        );
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName () {
        return  name;
    }

    public String getEmail () {
        return  email;
    }

    public String getPhone () {
        return  phone;
    }

    public String getImage () {
        return  image;
    }

    public Date getCreated_at () {
        return  created_at;
    }

    @Override
    public String getPassword () {
        return  password;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(userId, user.userId);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
