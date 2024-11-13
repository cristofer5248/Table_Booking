package com.chrisgeek.Edge.Service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Car {

    public Car(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}

