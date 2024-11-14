package com.chrisgeek.celupaz.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Entity(name = "Rsvp")
@NoArgsConstructor
public class Rsvp {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "rsvp_id")
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "seat_id")
    private Seat seatId;

    @Column(name ="customer_name", length = 100)
    private String customerName;



}
