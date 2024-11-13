package com.chrisgeek.Edge.Service.controller;

import com.chrisgeek.Edge.Service.entities.Mesa;
import com.chrisgeek.Edge.Service.entities.Seat;
import com.chrisgeek.Edge.Service.interfaces.CarClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
public class SeatController {

    private final CarClient mesaClient;
    private static final String CARCIRCUIT = "orderService2";

    public SeatController(CarClient mesaClient) {
        this.mesaClient = mesaClient;
    }
    private Collection<Mesa> fallbackSeats(Exception e) {
        System.out.println("Fallback method called new");
        return new ArrayList<>();
    }

    @GetMapping("/verAsientos")
    @CircuitBreaker(name = CARCIRCUIT, fallbackMethod = "fallbackSeats")
    public Collection<Seat> getSeats() {
        System.out.println("Inciando");
        ResponseEntity<Mesa> responseEntity;
        try {
            Collection<Seat> asientos = mesaClient.generarMesasCol()
                    .getContent()
                    .stream()
                    .filter(this::isCool)
                    .collect(Collectors.toList());
            return asientos;
        }
        catch (Exception e) {
            log.error("e: ", e);
            return null;
        }
    }
    private boolean isCool(Seat seat) {
        return true;
//        return !mesa.getTableId().equals("B");
//        return !mesa.getCordenadax();("AMC Gremlin") &&
//                !mesa.getName().equals("Triumph Stag") &&
//                !mesa.getName().equals("Ford Pinto") &&
//                !mesa.getName().equals("Yugo GV");
    }
}
