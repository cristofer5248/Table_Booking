package com.chrisgeek.celupaz.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@Entity(name = "Mesas")
@NoArgsConstructor
public class Mesa {

    public Mesa(int cordenaday, int cordenadax) {
        this.cordenaday = cordenaday;
        this.cordenadax = cordenadax;
    }

    @Id
    @Column(name = "TABLE_ID", length = 5)
    private String tableId;

    private int cordenadax;
    private int cordenaday;

    @OneToMany(mappedBy = "tableId")
    @JsonBackReference
    private List<Seat> seats;


}
