package ge.guka.CarCommerce.cars.persistence;

import ge.guka.CarCommerce.cars.user.persistence.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
//                                                                 რამდენით უნდა გაიზარდოს
@SequenceGenerator(name = "car_seq_gen", sequenceName = "car_seq", allocationSize = 1)
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(generator = "car_seq_gen", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "is_driveable")
    private boolean driveable;

    @Column(name = "image")
    private String imageUrl = "https://placehold.co/600x400";

    @Column(name = "price")
    private Double priceInCents = 0.0;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToMany(mappedBy = "ownedCars")
    private List<AppUser> owners = new ArrayList<>();

    @OneToMany(mappedBy = "car")
    private List<CarReview> reviews = new ArrayList<>();

}
