package com.chrisgeek.celupaz.repository;

import com.chrisgeek.celupaz.entities.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;


@RepositoryRestResource
interface MesasRepository  extends JpaRepository<Mesa, String> {
    Optional<Mesa> findFirstByTableId(String id);

    @Query("select m from Mesas m join fetch m.seats")
    List<Mesa> generarMesasYAsientos();
}