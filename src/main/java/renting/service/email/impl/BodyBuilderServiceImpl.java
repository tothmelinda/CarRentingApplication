package renting.service.email.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import renting.entity.MyUser;
import renting.service.email.BodyBuilderService;
import renting.service.token.RandomTokenService;

@Service
public class BodyBuilderServiceImpl implements BodyBuilderService {

    @Autowired
    RandomTokenService randomTokenService;


    @Override
    public String emailBody(MyUser myUser) {
        return "Hello," +
                "In order to activate your account please access the following link:\n" +
                "http://localhost:8080/activation/" + myUser.getRandomToken();
    }
}
