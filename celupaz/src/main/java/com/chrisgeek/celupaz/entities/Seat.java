package com.chrisgeek.celupaz.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity(name = "Seats")
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "seat_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "table_id")
    private Mesa tableId;

    @Column(name = "seat_number")
    private int seatNumber;

    private boolean reserved;
}
