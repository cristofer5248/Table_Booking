package com.chrisgeek.celupaz.repository;


import com.chrisgeek.celupaz.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;



public interface SeatsRepository  extends JpaRepository<Seat, Long> {

        @Query("Select s From Seats s join fetch s.tableId")
        List<Seat> generarMesas();
}