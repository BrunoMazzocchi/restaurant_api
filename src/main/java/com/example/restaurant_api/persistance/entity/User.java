package com.example.restaurant_api.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.*;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column
    private String name;
    @Column
    private String nickname;
    @Column(name = "user_email")
    private String email;
    @Column
    private String password;
    @Column
    private String phone;
    @Column
    private String image;
    @Column
    private Date created_at;
    @Column(nullable = false)
    private Boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

}

