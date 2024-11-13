package com.chrisgeek.Edge.Service.controller;

import com.chrisgeek.Edge.Service.entities.Mesa;
import com.chrisgeek.Edge.Service.interfaces.CarClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
public class MesaController {


    private static final String CARCIRCUIT = "orderService2";
    private final CarClient mesaClient;

    public MesaController(CarClient mesaClient) {
        this.mesaClient = mesaClient;
    }
    //private RestTemplate restTemplate = new RestTemplate();



    private Collection<Mesa> fallbackSeats(Exception e) {
        System.out.println("Fallback method called new");
        return new ArrayList<>();
    }



    @GetMapping("/vermesas")
    @CircuitBreaker(name = CARCIRCUIT, fallbackMethod = "fallbackSeats")
    public Collection<Mesa> getMesas() {
        System.out.println("Inciando");
        ResponseEntity<Mesa> responseEntity;

        try {
            Collection<Mesa> mesita = mesaClient.readMesas()
                    .getContent()
                    .stream()
                    .filter(this::isCool)
                    .collect(Collectors.toList());
            return mesita;
        }
        catch (Exception e) {
            log.error("e: ", e);
            return null;
        }

    }

    private boolean isCool(Mesa mesa) {
        return true;
//        return !mesa.getTableId().equals("B");
//        return !mesa.getCordenadax();("AMC Gremlin") &&
//                !mesa.getName().equals("Triumph Stag") &&
//                !mesa.getName().equals("Ford Pinto") &&
//                !mesa.getName().equals("Yugo GV");
    }
}

