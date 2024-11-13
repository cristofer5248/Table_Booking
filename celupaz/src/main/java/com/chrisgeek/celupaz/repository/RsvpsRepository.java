package com.chrisgeek.celupaz.repository;

import com.chrisgeek.celupaz.entities.Rsvp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
    interface RsvpsRepository extends JpaRepository<Rsvp, Long> {
    }
