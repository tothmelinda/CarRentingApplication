package renting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import renting.entity.Car;
import renting.entity.CarBrand;
import renting.entity.City;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Modifying
    @Transactional
    @Query(value="select * from car where car_brand like %:keyword% or car_city like %:keyword%", nativeQuery = true )

    List<Car> findByKeyword(@Param("keyword") String keyword);
    Set<Car> findAByCarBrandContaining(CarBrand carbrand);
    Set<Car> findByCity(City city);

}
