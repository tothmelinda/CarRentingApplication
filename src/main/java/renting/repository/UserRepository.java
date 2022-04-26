package renting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import renting.entity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long>
{
    MyUser findByUsernameIgnoreCase(String username);

    MyUser findByEmail(String username);

}
