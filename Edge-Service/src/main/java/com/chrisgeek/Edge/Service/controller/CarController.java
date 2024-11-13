package com.chrisgeek.Edge.Service.controller;

import com.chrisgeek.Edge.Service.entities.Car;
import com.chrisgeek.Edge.Service.interfaces.CarClient;
import com.chrisgeek.Edge.Service.interfaces.MesaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class CarController {


    private static final String CARCIRCUIT = "orderService";
    private final CarClient carClient;


    public CarController(CarClient carClient) {
        this.carClient = carClient;
    }
    //private RestTemplate restTemplate = new RestTemplate();



    private Collection<Car> fallbackGoodCars(Exception e) {
        System.out.println("Fallback method called");
        return new ArrayList<>();
    }

    @GetMapping("/buscarCar/{name}")
    @CircuitBreaker(name = CARCIRCUIT, fallbackMethod = "fallbackGoodCars")
    public Collection<Car> buscarCar(@PathVariable String name) {
        System.out.println("Iniciando búsqueda de coche por nombre: " + name);
        return carClient.findCarByNameContaining(name).getContent()
                .stream()
                .filter(this::isCool)
                .collect(Collectors.toList());
    }

    @GetMapping("/cool-cars")
    @CircuitBreaker(name = CARCIRCUIT, fallbackMethod = "fallbackGoodCars")
    public Collection<Car> getGoodCars() {
        System.out.println("Inciando");
        ResponseEntity<Car> responseEntity;
		return carClient.readCars()
			.getContent()
				.stream()
				.filter(this::isCool)
				.collect(Collectors.toList());
    }

    private boolean isCool(Car car) {
        return !car.getName().equals("AMC Gremlin") &&
                !car.getName().equals("Triumph Stag") &&
                !car.getName().equals("Ford Pinto") &&
                !car.getName().equals("Yugo GV");
    }
}

