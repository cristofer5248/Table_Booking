package com.chrisgeek.Edge.Service.interfaces;

import com.chrisgeek.Edge.Service.entities.Car;
import com.chrisgeek.Edge.Service.entities.Mesa;
import com.chrisgeek.Edge.Service.entities.Rsvp;
import com.chrisgeek.Edge.Service.entities.Seat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("celupaz")
public interface CarClient {
    @GetMapping("/cars")
    @CrossOrigin
    CollectionModel<Car> readCars();

    @GetMapping("/cars/search/findCarByNameContaining")
    @CrossOrigin
    CollectionModel<Car> findCarByNameContaining(@RequestParam("name") String name);

    @GetMapping("/mesas")
    @CrossOrigin
    CollectionModel<Mesa> readMesas();

    @GetMapping("/seats")
    @CrossOrigin
    CollectionModel<Seat> readSeats();

    @GetMapping("/generarMesas")
    @CrossOrigin
    List<Seat> generarMesas();

    @GetMapping("/generarMesas")
    @CrossOrigin
    CollectionModel<Seat> generarMesasCol();

    @GetMapping("/verReservados")
    @CrossOrigin
    List<Rsvp> obtenerReservados();
}

