package ge.guka.CarCommerce.cars.user.persistence;

import ge.guka.CarCommerce.cars.persistence.Car;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_user", schema = "cars")
@SequenceGenerator(name = "app_user_seq_gen", sequenceName = "app_user_seq", allocationSize = 1)
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(generator = "app_user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column(name = "balance")
    private Double balance;

    // fetch eager - მონაცემები უნდა ჩაიტვირთოს თავიდანვე როცა იუზერი მოგვაქ(და არა მოთხოვნისას როგორც lazy)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_car",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<Car> ownedCars = new ArrayList<>();
}
