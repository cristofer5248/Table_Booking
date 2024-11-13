package com.chrisgeek.celupaz.repository;


import com.chrisgeek.celupaz.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
interface CarRepository  extends JpaRepository<Car, Long> {

   //@Query("SELECT c FROM Car c where c.name = :name")
   List<Car> findCarByNameContaining(@Param("name") String name);
}
