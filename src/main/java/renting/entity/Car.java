package renting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString

public class Car {

    @Id
    @GeneratedValue
    private Long id;

    @Column (nullable = false, length = 20)
    private String carName;

    @Column
    private int year;

    @Column
    private boolean available;

    @Column(nullable = false, length = 3)
    private Integer quantity;

    @ManyToOne
    private RentalAndReturnDate rentalReturnDate;

    @ManyToOne
    private MyUser user;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = City.class)
    private Set<City> city;

//    @OneToOne(mappedBy = "cars")
//    @JsonIgnore
//    private CarType carType;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = CarBrand.class)
    private Set<CarBrand> carBrand;

}
