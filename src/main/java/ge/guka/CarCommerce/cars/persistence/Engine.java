package ge.guka.CarCommerce.cars.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "engine")
@SequenceGenerator(name = "engine_seq_gen", sequenceName = "engine_seq", allocationSize = 1)
public class Engine {

    @Id
    @GeneratedValue(generator = "engine_seq_gen", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "horse_power")
    private int horsePower;

    @Column(name = "capacity")
    private double capacity;

    public long getId() {
        return id;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}
