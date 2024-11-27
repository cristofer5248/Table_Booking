package com.chrisgeek.celupaz.repository;


import com.chrisgeek.celupaz.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;
import java.util.Optional;


@RepositoryRestResource
public interface SeatsRepository  extends JpaRepository<Seat, Long> {

        @Query("Select s From Seats s join fetch s.tableId")
        List<Seat> generarMesas();

        @Query("Select s From Seats s where concat(s.tableId,s.seatNumber) = :seatnumber")
        Optional<Seat> buscarSeatNumber(String seatnumber);
}