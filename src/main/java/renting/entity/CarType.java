package renting.entity;

import javax.persistence.*;

public class CarType {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String type;

    @Column(nullable = false, length = 20)
    private String colour;

//    @OneToOne(cascade = CascadeType.ALL)
//    private Car cars;

}
