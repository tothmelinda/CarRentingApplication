package renting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import renting.entity.RentalAndReturnDate;

@Repository
public interface RentalAndReturnDateRepository extends JpaRepository<RentalAndReturnDate, Long> {
}
