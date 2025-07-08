package com.autokolcsonzo.autokolcsonzo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String customerPhone;

    private LocalDate startDate;
    private LocalDate endDate;
    private int totalPrice;
}
