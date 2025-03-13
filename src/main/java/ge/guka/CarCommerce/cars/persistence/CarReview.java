package ge.guka.CarCommerce.cars.persistence;

import ge.guka.CarCommerce.cars.user.persistence.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carreview")
@SequenceGenerator(name = "review_seq_gen", sequenceName = "review_seq_gen", allocationSize = 1)
@Getter
@Setter
public class CarReview {

    @Id
    @GeneratedValue(generator ="review_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review")
    private String review;
}
