package com.f1istanbul.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grandstand;
    private String seatNumber;
    private Double price;
    private String barcode;
    private LocalDateTime purchaseDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
