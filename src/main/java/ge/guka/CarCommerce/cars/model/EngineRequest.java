package ge.guka.CarCommerce.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EngineRequest {
    private int horsePower;
    private double capacity;

}
