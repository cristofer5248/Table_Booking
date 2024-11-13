package com.chrisgeek.celupaz;

import com.chrisgeek.celupaz.entities.Car;
import com.chrisgeek.celupaz.entities.Mesa;
import com.chrisgeek.celupaz.entities.Seat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@EntityScan(basePackages = "com/chrisgeek/celupaz/entities")
@SpringBootApplication
public class CelupazApplication {

	public static void main(String[] args) {
		SpringApplication.run(CelupazApplication.class, args);
	}

//	@Bean
//	ApplicationRunner init(CarRepository repository) {
//		return args -> {
//			Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
//					"AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV").forEach(name -> {
//				repository.save(new Car(name));
//			});
//			repository.findAll().forEach(System.out::println);
//		};
//	}

}








//	@Component
//	class CarInitializer implements CommandLineRunner {
//
//		private final BeerRepository beerRepository;
//
//		BeerInitializer(BeerRepository beerRepository) {
//			this.beerRepository = beerRepository;
//		}





