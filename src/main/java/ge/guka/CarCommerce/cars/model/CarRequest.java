package ge.guka.CarCommerce.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarRequest {
    private String model;
    private int year;
    private boolean driveable;
    private Long engineId;
}
