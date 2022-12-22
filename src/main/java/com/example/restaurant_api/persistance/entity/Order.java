package com.example.restaurant_api.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.*;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;
    @Column
    private String address;
    @Column
    private Date date;
    @Column
    private String payment_method;
    @Column
    private String payment_status;
    @Column
    private String delivery_method;
    @Column
    private String delivery_status;
    @Column
    private String order_status;
    @Column
    private String order_type;
    @Column
    private String order_note;
    @Column
    private Double order_price;
    @Column
    private Double order_discount;
    @Column
    private Double order_total_price;
    @Column
    private Integer user_id;

    @ManyToMany
    @JoinTable(
            name = "order_food",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> foods;
}
