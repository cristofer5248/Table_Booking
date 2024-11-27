package com.chrisgeek.celupaz.controllers;

import com.chrisgeek.celupaz.entities.Clave;
import com.chrisgeek.celupaz.entities.Rsvp;
import com.chrisgeek.celupaz.entities.Seat;
import com.chrisgeek.celupaz.repository.ClavesRepository;
import com.chrisgeek.celupaz.repository.SeatsRepository;
import com.chrisgeek.celupaz.repository.RsvpsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SeatController {

    private final SeatsRepository seatsRepository;
    private final RsvpsRepository rvsprepository;
    private final ClavesRepository clavesRepository;

    public SeatController(SeatsRepository seatsRepository, RsvpsRepository rvsprepository, ClavesRepository clavesRepository) {
        this.seatsRepository = seatsRepository;
        this.rvsprepository = rvsprepository;
        this.clavesRepository = clavesRepository;
    }

    @GetMapping("/generarMesas")
    public List<Seat> getAllSeats() {
        return new ArrayList<>(seatsRepository.findAll());
    }
    @GetMapping("/verReservados")
    public List<Rsvp> getAllSeatsReservados() {
        return new ArrayList<>(rvsprepository.findAll());
    }

    @GetMapping("/buscarSeat/{id}")
    public Seat getAllSeatsReservados(@PathVariable("id") String id) {
        return seatsRepository.buscarSeatNumber(id).orElseThrow(() -> new RuntimeException("Seat not found"));
    }

    @PostMapping("/guardarrsvp")
    public String agregarrsvp(@RequestBody Rsvp rsvp) {
        System.out.println("ver los daos de rsvp "+rsvp);
        rvsprepository.save(rsvp);
        return "ok";
    }
    @GetMapping("/clavesbyclave")
    public Clave getAllClaves(@RequestParam String clave) {
        return clavesRepository.findByClavelist(clave).getFirst();
    }

}
