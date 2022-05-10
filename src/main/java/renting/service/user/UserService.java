package renting.service.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import renting.entity.MyUser;
import renting.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {



    MyUser findUserByEmail(String email);

    MyUser findUserByUserName(String userName);

    MyUser findUserByRandomToken(String randomToken);

    boolean findUserByUserNameAndPassword(String userName, String password);

    List<MyUser> findAll();

    void deleteById(long id);

    MyUser saveUser(MyUser u);


}
