package com.chrisgeek.celupaz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity(name = "Rsvp")
@NoArgsConstructor
public class Rsvp {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "rsvp_id")
    private Long Id;

    @Column(name = "seat_id")
    private int seatId;

    @Column(name ="customer_name", length = 100)
    private String customerName;

}
