package com.chrisgeek.Edge.Service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    @Column(name = "TABLE_ID")
    private String tableId;

    private int cordenadax;
    private int cordenaday;


}
