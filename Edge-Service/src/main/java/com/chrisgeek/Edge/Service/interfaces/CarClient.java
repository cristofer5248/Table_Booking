package com.chrisgeek.Edge.Service.interfaces;

import com.chrisgeek.Edge.Service.entities.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/rsvps/{id}")
    @CrossOrigin
    Rsvp obtenerRsvp(@PathVariable("id") Integer id);

    @PostMapping("/guardarrsvp")
    @CrossOrigin
    String guardarRsvp(@RequestBody Rsvp rsvp);

    @PutMapping("/rsvps/{id}")
    @CrossOrigin
    Rsvp actualizarRsvp(@PathVariable("id") Long id,@RequestBody Rsvp rsvp);//no sirve creo

    @PostMapping("/rsvps")
    @CrossOrigin
    Rsvp saveRsvp(@RequestBody Rsvp rsvp);

    @GetMapping("/claves/search/findByClave")
    @CrossOrigin
    Clave buscarclave(@RequestParam("nombre") String name);

    @PutMapping("/claves/{id}")
    @CrossOrigin
    void actualizarClave(@PathVariable("id") Long id,@RequestBody Clave clave);

    @GetMapping("/clavesbyclave?clave{clave}")
    @CrossOrigin
    Clave obtenerClave(@RequestParam("clave") String name);

    @PutMapping("/seats/{id}")
    void actualizarAsiento(@PathVariable("id") Long id, @RequestBody Seat seat);

    @GetMapping("/buscarSeat/{id}")
    Seat buscarMesaPorId(@PathVariable("id") String id);
}

