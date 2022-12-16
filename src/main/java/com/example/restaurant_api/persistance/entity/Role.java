package com.example.restaurant_api.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column
    private String description;
    private int state;

    @Enumerated(EnumType.STRING)
    @Column(length = 60, name = "role_name")
    private RoleName roleName;

}
