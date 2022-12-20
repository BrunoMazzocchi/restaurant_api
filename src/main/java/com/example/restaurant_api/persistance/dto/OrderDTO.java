package com.example.restaurant_api.persistance.dto;

import com.example.restaurant_api.persistance.entity.*;
import lombok.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Data
public class OrderDTO {
    private Integer order_id;
    private String address;
    private Date date;
    private String payment_method;
    private String payment_status;
    private String delivery_method;
    private String delivery_status;
    private String order_status;
    private String order_type;
    private String order_note;
    private Double order_price;
    private Double order_discount;
    private Double order_total_price;
    private Integer user_id;
    private List<Food> foods;
}
