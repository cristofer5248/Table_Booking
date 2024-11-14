package com.chrisgeek.celupaz.controllers;

import com.chrisgeek.celupaz.entities.Rsvp;
import com.chrisgeek.celupaz.entities.Seat;
import com.chrisgeek.celupaz.repository.SeatsRepository;
import com.chrisgeek.celupaz.repository.RsvpsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SeatController {

    private final SeatsRepository seatsRepository;
    private final RsvpsRepository rvsprepository;

    public SeatController(SeatsRepository seatsRepository, RsvpsRepository rvsprepository) {
        this.seatsRepository = seatsRepository;
        this.rvsprepository = rvsprepository;
    }

    @GetMapping("/generarMesas")
    public List<Seat> getAllSeats() {
        return new ArrayList<>(seatsRepository.findAll());
    }
    @GetMapping("/verReservados")
    public List<Rsvp> getAllSeatsReservados() {
        return new ArrayList<>(rvsprepository.findAll());
    }

}
