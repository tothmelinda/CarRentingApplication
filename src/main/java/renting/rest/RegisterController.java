package renting.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import renting.entity.MyUser;
import renting.entity.Role;
import renting.mapper.MyUserMapper;
import renting.rest.model.MyUserDTO;
import renting.service.user.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RegisterController {


    @Autowired
    private UserService userService;

    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        MyUser user = new MyUser();
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        model.addAttribute("user", user);
        return "register";
    }


    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute("user") @RequestBody MyUser user) {
        if (user.getPassword().equalsIgnoreCase(user.getPasswordConfirm())) {
            userService.saveUser(user);
            return "register-success";
        } else {
            return "register";
        }
    }

    @GetMapping(value = "/user/all")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll().stream().map(MyUserMapper::fromEntityToDTO).collect(Collectors.toList()));
        return "all-users";
    }

}
