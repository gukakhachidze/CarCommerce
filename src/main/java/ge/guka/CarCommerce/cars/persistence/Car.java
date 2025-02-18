package ge.guka.CarCommerce.cars.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
//                                                                 რამდენით უნდა გაიზარდოს
@SequenceGenerator(name = "car_seq_gen", sequenceName = "car_seq", allocationSize = 1)
public class Car {

    @Id
    @GeneratedValue(generator = "engine_seq_gen", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "is_driveable")
    private boolean driveable;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;


    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isDrivable() {
        return driveable;
    }

    public void setDrivable(boolean drivable) {
        this.driveable = drivable;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }


}
