package renting.rest.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import renting.entity.Car;
import renting.entity.ContactDetails;
import renting.entity.Role;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor


public class MyUserDTO {

    private Long id;

    private String username;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean accountNonExpired;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean accountNonLocked;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean credentialsNonExpired;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean enabled;

    private String fullName;

    private ContactDetails contactDetails;

    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Role> roles = new HashSet<>();

    private Set<Car> cars;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String passwordConfirm;

    public MyUserDTO(Long id, String username, String fullName, ContactDetails contactDetails, String email, Set<Car> cars) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.contactDetails = contactDetails;
        this.email = email;
        this.cars = cars;
    }
}
