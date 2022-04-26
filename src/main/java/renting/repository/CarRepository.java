package renting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.entity.Car;
import renting.entity.City;

import java.util.Set;

public interface CarRepository extends JpaRepository<Car, Long> {

//    Set<Car> findAByYearContaining(String name);
//
//    Set<Car> findByCity(City city);
}
