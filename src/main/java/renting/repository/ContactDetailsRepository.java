package renting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import renting.entity.ContactDetails;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long> {

}
