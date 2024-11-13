package com.chrisgeek.Edge.Service.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.chrisgeek.Edge.Service.entities.Mesa;
import com.chrisgeek.Edge.Service.entities.Seat;
import com.chrisgeek.Edge.Service.interfaces.CarClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@CrossOrigin
@Controller
public class MesasUIController {

    private final CarClient mesaClient;

    public MesasUIController(CarClient mesaClient) {
        this.mesaClient = mesaClient;
    }

    @GetMapping("/vermesasDisponibles")
    public String mesasDisponibles(Model model){
        //StringBuilder mesasFinal = new StringBuilder();
        System.out.println("Mesas disponibles");
        List<Seat> seats =  mesaClient.generarMesas();
        Set<String> mesas = seats.stream()
                .map(seat -> seat.getTableId().getTableId()).collect(Collectors.toSet());


        // Filtrar y agrupar los seatNumber por tableId, incluyendo coordenadas
        Map<String, Map<String, Object>> asientosPorMesa = new HashMap<>();
        mesas.forEach(mesa -> {
            List<Seat> asientosMesa = seats.stream()
                    .filter(seat -> mesa.equals(seat.getTableId().getTableId()))
                    .collect(Collectors.toList());

            if (!asientosMesa.isEmpty()) {
                Map<String, Object> detallesMesa = new HashMap<>();
                detallesMesa.put("seats", asientosMesa.stream()
                        .map(Seat::getSeatNumber)
                        .collect(Collectors.toList()));
                detallesMesa.put("x", asientosMesa.get(0).getTableId().getCordenadax());
                detallesMesa.put("y", asientosMesa.get(0).getTableId().getCordenaday());
                asientosPorMesa.put(mesa, detallesMesa);
            }
        });

        // Usar StringBuilder para construir la cadena de resultados
        StringBuilder mesasFinal = new StringBuilder();
        asientosPorMesa.forEach((mesa, detalles) -> {
            System.out.println("{\n id:'" + mesa + "',\n seats:" + detalles.get("seats") + ",\n x:" + detalles.get("x") + ",\n y:" + detalles.get("y") + "\n}");
            mesasFinal.append("{\n id:'").append(mesa).append("',\n seats:").append(detalles.get("seats"))
                    .append(",\n x:").append(detalles.get("x")).append(",\n y:").append(detalles.get("y")).append("\n},");
        });

        System.out.println(mesas);
        System.out.println("Reservador"+mesaClient.obtenerReservados());

        // Agregar los tableId únicos al modelo si es necesario

        mesasFinal.setLength(mesasFinal.length() - 1);
        model.addAttribute("mesas", mesasFinal);
        return "mesas/index";
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
