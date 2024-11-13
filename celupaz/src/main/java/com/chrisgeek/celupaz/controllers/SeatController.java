package com.chrisgeek.celupaz.controllers;

import com.chrisgeek.celupaz.entities.Seat;
import com.chrisgeek.celupaz.repository.SeatsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SeatController {

    private final SeatsRepository seatsRepository;

    public SeatController(SeatsRepository seatsRepository) {
        this.seatsRepository = seatsRepository;
    }

    @GetMapping("/generarMesas")
    public List<Seat> getAllSeats() {
        return new ArrayList<>(seatsRepository.findAll());
    }

}
