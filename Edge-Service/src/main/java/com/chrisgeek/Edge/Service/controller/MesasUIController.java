package com.chrisgeek.Edge.Service.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.chrisgeek.Edge.Service.entities.Mesa;
import com.chrisgeek.Edge.Service.entities.Rsvp;
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
        // Usar StringBuilder para construir la cadena de resultados
        StringBuilder mesasFinal = new StringBuilder();
        mesasFinal.append("var cfg = {\n tables: [");

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


        asientosPorMesa.forEach((mesa, detalles) -> {
            System.out.println("{\n id:'" + mesa + "',\n seats:" + detalles.get("seats") + ",\n x:" + detalles.get("x") + ",\n y:" + detalles.get("y") + "\n}");
            mesasFinal.append("{\n id:'").append(mesa).append("',\n seats:").append(detalles.get("seats"))
                    .append(",\n x:").append(detalles.get("x")).append(",\n y:").append(detalles.get("y")).append("\n},");
        });

        System.out.println(mesas);

        mesasFinal.append("],\n");

        List<Rsvp> rsvps = mesaClient.obtenerReservados();
        StringBuilder rsvpsFinal = new StringBuilder();
        rsvpsFinal.append("rsvp: {");
        for (Rsvp rsvp : rsvps) {
            String tableId = rsvp.getSeatId().getTableId().getTableId();
            int seatNumber = rsvp.getSeatId().getSeatNumber();
            String customerName = rsvp.getCustomerName();
            rsvpsFinal.append("  '").append(tableId).append(seatNumber).append("':'").append(customerName).append("',\n");
        }

        // Eliminar la última coma y salto de línea
        if (rsvpsFinal.length() > 7) {
            rsvpsFinal.setLength(rsvpsFinal.length() - 2);
        }

        rsvpsFinal.append("\n}}");
        mesasFinal.setLength(mesasFinal.length() - 1);
        mesasFinal.append(rsvpsFinal);
        System.out.println("Reservador"+rsvpsFinal);

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
