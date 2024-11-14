package com.chrisgeek.celupaz.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

//    @OneToMany(mappedBy = "Id")
//    @JsonBackReference
//    private List<Rsvp> rsvps;
}
