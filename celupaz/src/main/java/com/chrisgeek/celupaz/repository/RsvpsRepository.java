package com.chrisgeek.celupaz.repository;

import com.chrisgeek.celupaz.entities.Rsvp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
    public interface RsvpsRepository extends JpaRepository<Rsvp, Long> {

//    @Query("Select r From Rsvp r join fetch r.seatId")
//    List<Rsvp> verReservados();
    }
