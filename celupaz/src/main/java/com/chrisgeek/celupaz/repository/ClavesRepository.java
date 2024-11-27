package com.chrisgeek.celupaz.repository;

import com.chrisgeek.celupaz.entities.Clave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ClavesRepository extends JpaRepository<Clave, Long> {

    @Query("Select c From Clave c where c.clave=:nombre and c.usada=0")
    Clave findByClave(String nombre);

    @Query("Select c From Clave c where c.clave=:nombre and c.usada=0")
    List<Clave> findByClavelist(String nombre);


}
